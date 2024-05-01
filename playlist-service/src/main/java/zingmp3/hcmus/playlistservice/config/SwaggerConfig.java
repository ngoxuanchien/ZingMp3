package zingmp3.hcmus.playlistservice.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

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

    @Value("${server.url}")
    private String gatewayUrl;

    @Value("${keycloak.token-url}")
    private String tokenUrl;

    @Bean
    public GroupedOpenApi playlistOpenApi(@Value("${springdoc.version}") String appVersion) {
        String[] paths = {
                "/api/playlist/**"
        };

        List<Server> servers = List.of(
                new Server().url("/").description("Playlist service server"),
                new Server().url(gatewayUrl).description("Production server")
        );
        return GroupedOpenApi.builder()
                .group("playlist-service")
                .addOpenApiCustomizer(openApi -> openApi
                        .servers(servers)
                        .info(new io.swagger.v3.oas.models.info.Info().title("Playlists API").version(appVersion)))
                .pathsToMatch(paths)
                .build();

    }
}
