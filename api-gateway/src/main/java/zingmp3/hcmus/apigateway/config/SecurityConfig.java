package zingmp3.hcmus.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import zingmp3.hcmus.apigateway.converter.JwtAuthConverter;

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
            "/api/music/**",
            "/api/register/**",
            "/images/**",
    };

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers(WHILE_LIST_URL)
                            .permitAll()
                        .anyExchange()
                            .permitAll())
                .oauth2ResourceServer(spec -> spec
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
                );
        return http.build();
    }


}
