package zingmp3.hcmus.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("search-service", r -> r
                        .path("/api/song/**")
                        .uri("lb://search-service"))
                .route("user-service", r-> r
                        .path("/api/auth/**")
                        .uri("lb://user-service"))
                .build();
    }
}
