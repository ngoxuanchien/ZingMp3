//package zingmp3.config;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
//import zingmp3.converter.JwtAuthConverter;
//
//
//@Configuration
//@EnableWebFluxSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final JwtAuthConverter jwtAuthConverter;
//
//    private final String[] WHITELIST = {
//            "/v3/api-docs/**",
//            "/swagger-ui/**",
//            "/swagger-ui.html",
//            "/api/songs/**",
//            "/api/genres/**",
//            "/api/artists/**",
//            "/api/albums/**",
//            "/api/composers/**"
//    };
//
////    @Bean
////    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
////        http.csrf(AbstractHttpConfigurer::disable)
//////                .authorizeHttpRequests(auth -> auth
//////                        .requestMatchers(HttpMethod.GET, WHITELIST).permitAll()
//////                        // song authorities
//////                        .requestMatchers(HttpMethod.POST, "/api/songs/**").hasAuthority("song-create")
//////                        .requestMatchers(HttpMethod.PUT, "/api/songs/**").hasAuthority("song-update")
//////                        .requestMatchers(HttpMethod.DELETE, "/api/songs/**").hasAuthority("song-delete")
//////
//////                        // genre authorities
//////                        .requestMatchers(HttpMethod.POST, "/api/genres/**").hasAuthority("genre-create")
//////                        .requestMatchers(HttpMethod.PUT, "/api/genres/**").hasAuthority("genre-update")
//////                        .requestMatchers(HttpMethod.DELETE, "/api/genres/**").hasAuthority("genre-delete")
//////
//////                        // album authorities
//////                        .requestMatchers(HttpMethod.POST, "/api/albums/**").hasAuthority("album-create")
//////                        .requestMatchers(HttpMethod.PUT, "/api/albums/**").hasAuthority("album-update")
//////                        .requestMatchers(HttpMethod.DELETE, "/api/albums/**").hasAuthority("album-delete")
//////
//////                        // artist authorities
//////                        .requestMatchers(HttpMethod.POST, "/api/artists/**").hasAuthority("artist-create")
//////                        .requestMatchers(HttpMethod.PUT, "/api/artists/**").hasAuthority("artist-update")
//////                        .requestMatchers(HttpMethod.DELETE, "/api/artists/**").hasAuthority("artist-delete")
//////
//////                        // composer authorities
//////                        .requestMatchers(HttpMethod.POST, "/api/composers/**").hasAuthority("composer-create")
//////                        .requestMatchers(HttpMethod.PUT, "/api/composers/**").hasAuthority("composer-update")
//////                        .requestMatchers(HttpMethod.DELETE, "/api/composers/**").hasAuthority("composer-delete")
//////
//////                        .anyRequest()
//////                        .authenticated())
//////                .addFilterAfter(createPolicyEnforcerFilter(), BearerTokenAuthenticationFilter.class)
////                .oauth2ResourceServer(oauth2 -> oauth2
////                        .jwt(jwt -> jwt
////                                .jwtAuthenticationConverter(jwtAuthConverter)))
////                .sessionManagement(session -> session
////                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////
////        return http.build();
////    }
//
////    private ServletPolicyEnforcerFilter createPolicyEnforcerFilter() {
////        return new ServletPolicyEnforcerFilter(new ConfigurationResolver() {
////            @Override
////            public PolicyEnforcerConfig resolve(HttpRequest httpRequest) {
////                try {
////                    return JsonSerialization.readValue(getClass().getResourceAsStream("/policy-enforcer.json"), PolicyEnforcerConfig.class);
////                } catch (IOException e) {
////                    throw new RuntimeException(e);
////                }
////            }
////        });
////    }
//
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    @Bean
//    SecurityWebFilterChain apiHttpSecurity(ServerHttpSecurity http) {
//        http
////                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/api/**"))
////                .authorizeExchange(exchange -> exchange
////                        .pathMatchers(WHITELIST).permitAll()
////                        .anyExchange().authenticated()
////                )
//                .authorizeExchange(exchange -> exchange
//                        .anyExchange().permitAll()
//                )
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwt -> jwt
//                                .jwtAuthenticationConverter(jwtAuthConverter))
//                );
//
//        return http.build();
//    }
//}
