package hcmus.zingmp3.music_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

@OpenAPIDefinition(
        servers = {
                @Server(url = "/", description = "Default Server URL"),
                @Server(url = "${server.url}", description = "Production server")
        },
        info = @Info(title = "Song Service API", version = "1.0", description = "API for Song Service")
)
@SecurityScheme(
        name = "Keycloak",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                password = @OAuthFlow(
                        tokenUrl = "${keycloak.token-url}",
                        refreshUrl = "${keycloak.token-url}",
                        scopes = {
                                @OAuthScope(name = "openid", description = "openid scope")
                        }
                )
        )
)
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi musicOpenAPI(@Value("1") String appVersion) {
        String[] paths = {
                "/api/music/**"
        };
        String[] packagedToMatch = {
                "hcmus.zingmp3.music_service.controller"
        };
        return GroupedOpenApi.builder()
                .group("music-service")
                .addOpenApiCustomizer(openApi -> openApi.info(new io.swagger.v3.oas.models.info.Info().title("Song service API").version(appVersion)))
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch)
                .build();
    }
}
