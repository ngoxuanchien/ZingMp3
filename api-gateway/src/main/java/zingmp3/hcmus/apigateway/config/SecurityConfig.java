package zingmp3.hcmus.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import zingmp3.hcmus.apigateway.converter.JwtAuthConverter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

//    private final CorsConfigurationSource corsConfigurationSource;
    private final JwtAuthConverter jwtAuthConverter;

    private static final String[] WHILE_LIST_URL = {
            "/eureka/**",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/api/auth/**",
            "/api/songs/**",
            "/api/register/**",
            "/api/genres/**",
            "/api/artists/**",
            "/api/albums/**",
            "/api/composers/**"
    };

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchange -> exchange.anyExchange().permitAll())
                .authorizeExchange(exchange ->
                        exchange.pathMatchers(WHILE_LIST_URL)
                                .permitAll()
                                .anyExchange()
                                .authenticated())
                .oauth2ResourceServer(spec -> spec
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
                );
        return http.build();
    }


}
