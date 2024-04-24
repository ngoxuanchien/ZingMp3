package zingmp3.hcmus.playlistservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import zingmp3.hcmus.playlistservice.converter.JwtAuthConverter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthConverter jwtAuthConverter;

    private final String[] WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, WHITELIST).permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/playlist/**").hasAuthority("playlist-create")
//                        .requestMatchers(HttpMethod.PUT, "/api/playlist/**").hasAuthority("playlist-update")
//                        .requestMatchers(HttpMethod.DELETE, "/api/playlist/**").hasAuthority("playlist-delete")
//                        .requestMatchers(HttpMethod.POST, "/api/playlist/**").hasAuthority("playlist-add-song")
//                        .requestMatchers(HttpMethod.DELETE, "/api/playlist/**").hasAuthority("playlist-remove-song")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                                .jwt(jwt -> jwt
                                        .jwtAuthenticationConverter(jwtAuthConverter)))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }
}
