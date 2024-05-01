package zingmp3.hcmus.playlistservice;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class FluxLearnTest {

    @Service
    public class FluxLearnService {
        // all operators example here

        public Flux<String> getFlux() {
            return Flux.just("Alice", "Bob", "Charlie");
        }

        public Flux<String> fruitsFlux() {
            List<String> fruitsName = List.of("Apple", "Banana", "Orange");
            return Flux.fromIterable(fruitsName);
        }

        public Flux<Void> getBlankFlux() {
            return Flux.empty();
        }

//        map
        public Flux<String> mapExampleFlux() {
            Flux<String> capFlux = getFlux().map(String::toUpperCase).log();
            return capFlux;
        }

        // filter
        public Flux<String> filterExampleFlux() {
            return getFlux().filter(s -> s.length() > 4).log();
        }

        // flatMap
        public Flux<String> flatMapExampleFlux() {
            return getFlux().flatMap(name -> Flux.just(name.split(""))).delayElements(Duration.ofSeconds(2)).log();
//            return getFlux().flatMap(name -> Flux.just("Text Flux")).delayElements(Duration.ofSeconds(2)).log();
        }

        // transform example
        public Flux transformExample() {
            Function<Flux<String>, Flux<String>> funInterFace =  (name) -> name.map(String::toUpperCase).log();

            return getFlux().transform(funInterFace);
        }

        // defaultIfEmpty
        // switchIfEmpty
        public Flux<String> ifExample(int length) {
            return getFlux().filter(name -> name.length() > length)
//                    .defaultIfEmpty("No name")
                    .switchIfEmpty(fruitsFlux())
                    .log();
        }

        // concat(static) / concatWith(instance)
        public Flux<String> concatExample() {
//            return Flux.concat(getFlux(), fruitsFlux()).log();
            return getFlux().delayElements(Duration.ofSeconds(1))
                    .concatWith(fruitsFlux().delayElements(Duration.ofSeconds(2)))
                    .log();
        }

        // merge and mergeWith
        public Flux<String> mergeExample() {
//            return getFlux().mergeWith(fruitsFlux()).log();
            return Flux.merge(getFlux().delayElements(Duration.ofSeconds(1)), fruitsFlux().delayElements(Duration.ofSeconds(2))).log();
        }

        // zip and zipWith example
        public Flux<String> zipExample() {
//            return Flux.zip(getFlux(), Flux.just(1,2,3,4)).log();
            return Flux.zip(getFlux(), Flux.just(1, 2, 3), (first, second) -> {
                return first + ": " + second;
            }).log();
        }

        public Flux<String> sideEffectFlux() {
            return getFlux().doOnNext(data -> {
                System.out.println(data + " on Next");
            }).doOnSubscribe(data -> {
                System.out.println(data + " on subscribe");
            }).doOnEach(data -> {
                System.out.println(data + " each");
            }).doOnComplete(() -> {
                System.out.println("completed");
            });
        }
    }

    private FluxLearnService fluxLearnService = new FluxLearnService();

    @Test
    void testing() {
//        fluxLearnService.getFlux().subscribe(data -> {
//            System.out.println(data);
//            System.out.println("done with flux data");
//        });
        fluxLearnService.fruitsFlux().subscribe(System.out::println);
    }

    @Test
    public void mapTest() {

        Flux<String> capFlux = fluxLearnService.mapExampleFlux();
        StepVerifier.create(capFlux)
                .expectNextCount(3)
                .verifyComplete();

    }

    @Test
    void filterTest() {
        Flux<String> filterFlux = fluxLearnService.filterExampleFlux();
        StepVerifier.create(filterFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void flatmapTest() {
        Flux<String> stringFlux = fluxLearnService.flatMapExampleFlux();
        StepVerifier.create(stringFlux)
                .expectNextCount(15)
                .verifyComplete();

    }

    @Test
    void transformTest() {
        Flux flux = fluxLearnService.transformExample();
        StepVerifier.create(flux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void ifExample() {
        Flux<String> stringFlux = fluxLearnService.ifExample(4);
        StepVerifier.create(stringFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void concatExample() {
        Flux<String> stringFlux = fluxLearnService.concatExample();
        StepVerifier.create(stringFlux)
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    void mergeExample() {
        Flux<String> stringFlux = fluxLearnService.mergeExample();
        StepVerifier.create(stringFlux)
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    void zipExample() {
        Flux<String> tuple2Flux = fluxLearnService.zipExample();
        StepVerifier.create(tuple2Flux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void sideEffectTest() {
        fluxLearnService.sideEffectFlux().log().subscribe(System.out::println);
    }
}
