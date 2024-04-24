package zingmp3.hcmus.playlistservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.ValueConverter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@OpenAPIDefinition(
		servers = {
				@Server(url = "http://localhost:8084", description = "song-service server"),
				@Server(url = "http://localhost:8081", description = "api-gateway server"),
				@Server(url = "http://nxc-hcmus.me:8081", description = "Production server")
		},
		info = @Info(title = "Song Service API", version = "1.0", description = "API for Song Service")
)
@SecurityScheme(
		name = "Keycloak",
		type = SecuritySchemeType.OAUTH2,
		flows = @OAuthFlows(
				password = @OAuthFlow(
//						tokenUrl = "http://localhost:8080/realms/zing-mp3/protocol/openid-connect/token",
//						tokenUrl = "http://nxc-hcmus.me:8081/api/auth/token",
						tokenUrl = "${keycloak.tokenUrl}",
						refreshUrl = "${keycloak.tokenUrl}",
						scopes = {
								@OAuthScope(name = "openid", description = "openid scope")
						}
				)
		)
)
public class PlaylistServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlaylistServiceApplication.class, args);
	}

	@Value("${eureka.client.service-url.default-zone}")
	private String eurekaUrl;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("Eureka URL: " + eurekaUrl);
		};
	}
}
