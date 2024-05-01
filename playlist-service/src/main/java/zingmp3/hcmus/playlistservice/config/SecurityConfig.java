package zingmp3.hcmus.playlistservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import zingmp3.hcmus.playlistservice.converter.JwtAuthConverter;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(auth -> auth

                        .pathMatchers(HttpMethod.POST,"/api/playlist/**")
                        .authenticated()
                        .pathMatchers(HttpMethod.PUT,"/api/playlist/**")
                        .authenticated()
                        .pathMatchers(HttpMethod.DELETE,"/api/playlist/**")
                        .hasRole("admin")

                        .anyExchange()
                        .permitAll())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)));
        return http.build();
    }
}
