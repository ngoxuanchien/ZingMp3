package hcmus.mp3.web.config;

import hcmus.mp3.web.config.converter.JwtAuthConverter;
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
            "/api/play/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers(WHITELIST)
                        .permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/audios/**")
                        .permitAll()

//                        .requestMatchers(HttpMethod.GET, "/api/play/**")
//                        .hasAnyRole("DISTRIBUTOR", "ADMIN", "USER")

                        .requestMatchers(HttpMethod.POST, "/api/audios/**")
                        .hasAnyRole("DISTRIBUTOR", "ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/audios/**")
                        .hasRole("ADMIN")

                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthConverter)));
        return http.build();
    }
}
