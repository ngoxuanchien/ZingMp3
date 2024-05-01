package hcmus.zingmp3.playbackservice.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi streamingOpenApi(@Value("${springdoc.version}") String appVersion) {
        String[] paths = {
                "/api/playback/**"
        };
        return GroupedOpenApi.builder()
                .group("playback-service")
                .addOpenApiCustomizer(openApi -> openApi
                        .info(new io.swagger.v3.oas.models.info.Info().title("Playback service API").version(appVersion)))
                .pathsToMatch(paths)
                .build();
    }
}
