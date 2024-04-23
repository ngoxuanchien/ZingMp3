package zingmp3;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@OpenAPIDefinition(
		servers = {
				@Server(url = "http://localhost:8083", description = "user-service server"),
				@Server(url = "http://localhost:8081", description = "api-gateway server"),
				@Server(url = "http://nxc-hcmus.me:8081", description = "Production server")
		},
		info = @Info(title = "User Service API", version = "1.0", description = "API for User Service")
)
@SecurityScheme(
		name = "Keycloak",
		type = SecuritySchemeType.OAUTH2,
		flows = @OAuthFlows(
				password = @OAuthFlow(
//						tokenUrl = "http://localhost:8080/realms/zing-mp3/protocol/openid-connect/token",
//						tokenUrl = "http://nxc-hcmus.me:8081/api/auth/token",
						tokenUrl = "${keycloak.token-url}",
						refreshUrl = "${keycloak.token-url}",
						scopes = {
								@OAuthScope(name = "openid", description = "openid scope")
						}
				)
		)
)
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
