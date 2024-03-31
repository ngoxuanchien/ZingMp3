package hcmus.zingmp3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("eureka-service", r -> r.path("/eureka/web")
                        .filters(f -> f.setPath("/"))
                        .uri("http://localhost:8761"))
                .route("eureka-service-static", r -> r.path("/eureka/**")
                        .uri("http://localhost:8761"))
                .route("user-service", r -> r.path("/api/demo", "/api/auth/**")
                        .uri("lb://user-service"))
                .build();
    }
}
