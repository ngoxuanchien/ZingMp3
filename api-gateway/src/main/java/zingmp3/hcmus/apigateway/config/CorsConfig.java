//package zingmp3.hcmus.apigateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsConfigurationSource;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class CorsConfig {
//
//    private final String[] ALLOWED_ORIGINS = {
//            "http://localhost:8081",
//            "http://nxc-hcmus.me:8081",
//            "http://localhost:8082",
//            "http://localhost:3000"
//    };
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of(ALLOWED_ORIGINS));
//        configuration.setAllowedMethods(List.of(CorsConfiguration.ALL));
//        configuration.setAllowedHeaders(List.of(CorsConfiguration.ALL));
//        org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//}
