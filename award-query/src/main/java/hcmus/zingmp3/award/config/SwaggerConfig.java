package hcmus.zingmp3.award.swagger;

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

@OpenAPIDefinition(
        servers = {
                @Server(url = "/", description = "Default Server URL")
        },
        info = @Info(title = "Award Command Service API", version = "1.0", description = "API for Award Command Service")
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
    public GroupedOpenApi awardOpenAPI(@Value("1") String appVersion) {
        String[] paths = {
                "/api/award/**"
        };
        return GroupedOpenApi.builder()
                .group("award-command-service")
                .addOpenApiCustomizer(openApi -> openApi.info(new io.swagger.v3.oas.models.info.Info().title("Award Command service API").version(appVersion)))
                .pathsToMatch(paths)
                .build();
    }
}