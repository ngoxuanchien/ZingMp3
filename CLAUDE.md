# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

ZingMp3 backend: a Spring Boot (Java 21) microservice system. It uses a Maven multi-module build. The root `pom.xml` is an aggregator with groupId `hcmus.zingmp3`.

## Commands

```bash
# Start infra first: Keycloak :8080, Kafka cluster :9092-9094, ZooKeeper, schema registry :8082, per-service MySQL
cd init && docker-compose up -d && cd ..

mvn clean install                         # build everything (generate-sources must install first; the reactor handles it)
mvn clean install -DskipTests             # what CI uses on non-master branches
mvn test                                  # all tests
mvn -pl artist-service/artist-core -am test   # one module (+ upstream deps)
mvn -pl artist-service/artist-core -am test -Dtest=ArtistCoreApplicationTests -Dsurefire.failIfNoSpecifiedTests=false   # one test class

cd artist-service/artist-core && mvn spring-boot:run   # run a single service
docker-compose up -d                      # run all services from published images (root docker-compose.yml)
```

- Run `discovery-service` (Eureka, :8761) first. All other services register with it. Everything goes through `api-gateway` (:8081).
- Docker images are built with Jib (`mvn ... jib:build`), pushed to `registry.hub.docker.com/ngoxuanchien/${artifactId}`. Jib is set to `skip` in the root pom and in `generate-sources`.
- The existing tests are only `contextLoads` smoke tests. They need the infra running (MySQL, Kafka, Keycloak, Eureka).
- `aggregator-service` (an empty skeleton) and `clone-data` are **not** modules in the root pom. `clone-data/` is in `.gitignore`, so only some of its files are tracked.
- `search-service` (Elasticsearch, infra in `init/elastic-search/`) is not wired in anywhere yet: it's missing from the root pom, the gateway routes and the root `docker-compose.yml`. Build it with `mvn -f search-service/pom.xml install`.
- `notification-core` needs the `MAIL_USERNAME`/`MAIL_PASSWORD` env vars. In CI they're written to `.env` for docker-compose.

## Architecture

**Infra:** Eureka for discovery, Spring Cloud Gateway for routing, Keycloak for auth (realm `zing-mp3`, JWT resource servers), Kafka for events, MySQL per service. Services listen on `server.port: 0` (random) and gRPC servers on port 0. Callers resolve them via Eureka: HTTP via `lb://<name>`, gRPC via `discovery:///<name>`. Gateway routes live in `api-gateway/src/main/resources/application.yml`. Add a route there when you add a new path prefix.

**`generate-sources`:** a shared module holding the `.proto` files (`src/main/proto`). `protobuf-maven-plugin` compiles them into gRPC stubs, and every service depends on this module. To change an inter-service gRPC contract, edit the proto there and rebuild/install that module first. The Avro schemas in `src/main/resources/avro/*.avsc` are unused. Kafka events are plain JSON: `JsonSerializer` on the producer side, parsed with Gson in the consumers.

**Synchronous calls:** gRPC (`net.devh` grpc-spring-boot-starter). Servers are `@GrpcService` classes (e.g. `artist-core/.../service/grpc`). Clients use `@GrpcClient` inside `service/<target>/…ServiceImpl` wrappers (e.g. song-core calls artist, image and media). The validators in `web/dto/validator` (e.g. `@ImageExists`) check over gRPC that referenced resources exist.

**CQRS / event-sourcing split** (artist, song, playlist, search). Each has three submodules:
- `*-common`: JPA entities (`domain/model`), event classes (`events/`, subclasses of `AbstractEvent` with a `type`), repositories, and the **query** services. Both runnable modules share it.
- `*-core`: the REST API (`web/`), gRPC server, and **command** services. A command never writes the entity directly. It creates an event (`ArtistCreateEvent`, …), and `EventServiceImpl` saves the event to the event table, then publishes it to the Kafka topic (`kafka.topic.name`, e.g. `artist-service`). Its JPA setting is `ddl-auto: update`.
- `*-event-handler`: a `@KafkaListener` (`EventConsumerImpl`) parses the JSON payload with Gson. It dispatches on `type` through a `Map<String, EventHandler>`, where each handler is a bean named after the event type (`@Component("ARTIST_CREATE")`). That handler applies the change to the read/write entity table and may emit notification events. Its JPA setting is `ddl-auto: none`, against the same DB.

So a write in `*-core` only becomes visible after the event handler processes it. To add an event: add the event class + `EventType` in common, emit it from the command service, and add a handler bean whose name matches the type string.

**Simple services** (`user-service`, `image-service`, `media-service`, `notification-service`) are single Spring Boot apps with conventional controller → service → repository layering. `media-service` streams audio, and `notification-core` consumes Kafka events and sends email.

**Authorization:** `api-gateway` lets every request through. Each service enforces roles (`ADMIN`, `DISTRIBUTOR`, `USER`, mapped from Keycloak by `JwtAuthConverter`) in its own `SecurityConfig`. Matchers are first-match-wins, so specific paths (e.g. `/api/albums/approved/**`) must come before wildcards (e.g. `PUT /api/albums/**`). End the chain with `.anyRequest()`.

**Package naming is inconsistent:** most services use `hcmus.zingmp3`, but `image-service`/`media-service` use `hcmus.mp3` and `api-gateway`/`discovery-service` use `zingmp3.hcmus`. Follow whatever the module you're editing already uses.

**Config:** each service's `application.yml` uses `${ENV_VAR:default}` placeholders. The defaults point at localhost infra (e.g. artist MySQL on port 3336). The root `docker-compose.yml` overrides them for containers.

## Coding Guidelines

Behavioral guidelines to reduce common LLM coding mistakes.

**Tradeoff:** These guidelines bias toward caution over speed. For trivial tasks, use judgment.

### 1. Think Before Coding

**Don't assume. Don't hide confusion. Surface tradeoffs.**

Before implementing:
- State your assumptions explicitly. If uncertain, ask.
- If multiple interpretations exist, present them - don't pick silently.
- If a simpler approach exists, say so. Push back when warranted.
- If something is unclear, stop. Name what's confusing. Ask.

### 2. Simplicity First

**Minimum code that solves the problem. Nothing speculative.**

- No features beyond what was asked.
- No abstractions for single-use code.
- No "flexibility" or "configurability" that wasn't requested.
- No error handling for impossible scenarios.
- If you write 200 lines and it could be 50, rewrite it.

Ask yourself: "Would a senior engineer say this is overcomplicated?" If yes, simplify.

### 3. Surgical Changes

**Touch only what you must. Clean up only your own mess.**

When editing existing code:
- Don't "improve" adjacent code, comments, or formatting.
- Don't refactor things that aren't broken.
- Match existing style, even if you'd do it differently.
- If you notice unrelated dead code, mention it - don't delete it.

When your changes create orphans:
- Remove imports/variables/functions that YOUR changes made unused.
- Don't remove pre-existing dead code unless asked.

The test: Every changed line should trace directly to the user's request.

### 4. Goal-Driven Execution

**Define success criteria. Loop until verified.**

Transform tasks into verifiable goals:
- "Add validation" → "Write tests for invalid inputs, then make them pass"
- "Fix the bug" → "Write a test that reproduces it, then make it pass"
- "Refactor X" → "Ensure tests pass before and after"

For multi-step tasks, state a brief plan:
```
1. [Step] → verify: [check]
2. [Step] → verify: [check]
3. [Step] → verify: [check]
```

Strong success criteria let you loop independently. Weak criteria ("make it work") require constant clarification.

---

**These guidelines are working if:** fewer unnecessary changes in diffs, fewer rewrites due to overcomplication, and clarifying questions come before implementation rather than after mistakes.
