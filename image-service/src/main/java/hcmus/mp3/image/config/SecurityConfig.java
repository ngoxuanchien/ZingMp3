package hcmus.mp3.image.config;

import hcmus.mp3.image.config.converter.JwtAuthConverter;
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

    private static final String[] WHITELIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/thumbnail/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers(WHITELIST)
                        .permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/images/**")
                        .hasAnyRole("ADMIN", "DISTRIBUTOR")

                        .requestMatchers(HttpMethod.PUT, "/api/images/**")
                        .hasAnyRole("ADMIN", "DISTRIBUTOR")

                        .requestMatchers(HttpMethod.DELETE, "/api/images/**")
                        .hasRole("ADMIN")

                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthConverter)));
        return http.build();
    }
}
