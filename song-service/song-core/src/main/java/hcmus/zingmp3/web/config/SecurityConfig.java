package hcmus.zingmp3.web.config;

import hcmus.zingmp3.web.config.converter.JwtAuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    private final String[] WHITELIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/actuator/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers(WHITELIST)
                        .permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/songs/my-songs")
                        .authenticated()

                        .requestMatchers(HttpMethod.GET, "/api/songs/**", "/api/genres/**")
                        .permitAll()

                        .requestMatchers(HttpMethod.POST)
                        .hasAnyRole("DISTRIBUTOR", "ADMIN")

                        .requestMatchers("/api/songs/approved/**", "/api/songs/rejected/**", "/api/songs/released/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE)
                        .hasRole("ADMIN")

                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthConverter)));
        return http.build();
    }
}
