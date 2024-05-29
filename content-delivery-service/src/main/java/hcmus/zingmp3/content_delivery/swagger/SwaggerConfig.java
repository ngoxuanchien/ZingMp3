package hcmus.zingmp3.content_delivery.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Configuration
@OpenAPIDefinition(
        servers = {
                @Server(url = "/", description = "Production server")
        },
        info = @Info(title = "Song Service API", version = "1.0", description = "API for Song Service")
)
@SecurityScheme(
        name = "Keycloak",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                password = @OAuthFlow(
                        tokenUrl = "${keycloak.token-url}",
                        refreshUrl = "${keycloak.token-url}"
                )
        )
)
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi streamingOpenApi(@Value("1") String appVersion) {
        String[] paths = {
                "/api/content-delivery/**"
        };
        return GroupedOpenApi.builder()
                .group("content-delivery")
                .addOpenApiCustomizer(openApi -> openApi
                        .info(new io.swagger.v3.oas.models.info.Info().title("Playback service API").version(appVersion)))
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public MappingJackson2HttpMessageConverter octetStreamJsonConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(
                new MediaType("application", "octet-stream")));
        return converter;
    }
}
