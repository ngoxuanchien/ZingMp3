package hcmus.zingmp3.content_delivery.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Configuration
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
