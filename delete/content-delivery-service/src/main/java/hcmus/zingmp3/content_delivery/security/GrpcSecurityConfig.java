//package hcmus.zingmp3.content_delivery.security;
//
//import lombok.RequiredArgsConstructor;
//import net.devh.boot.grpc.server.security.authentication.BearerAuthenticationReader;
//import net.devh.boot.grpc.server.security.authentication.CompositeGrpcAuthenticationReader;
//import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
//import net.devh.boot.grpc.server.security.check.AccessPredicate;
//import net.devh.boot.grpc.server.security.check.AccessPredicateVoter;
//import net.devh.boot.grpc.server.security.check.GrpcSecurityMetadataSource;
//import net.devh.boot.grpc.server.security.check.ManualGrpcSecurityMetadataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDecisionVoter;
//import org.springframework.security.access.vote.UnanimousBased;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//public class GrpcSecurityConfig {
//    private final JwtAuthConverter jwtAuthConverter;
//
//    @Bean
//    AuthenticationManager authenticationManager(JwtDecoder jwtDecoder) {
//        final List<AuthenticationProvider> providers = new ArrayList<>();
//        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(jwtDecoder);
//        jwtAuthenticationProvider.setJwtAuthenticationConverter(jwtAuthConverter);
//        providers.add(jwtAuthenticationProvider); // Possibly JwtAuthenticationProvider
//
//        return new ProviderManager(providers);
//    }
//
//    @Bean
//    GrpcAuthenticationReader authenticationReader() {
//        final List<GrpcAuthenticationReader> readers = new ArrayList<>();
//
//        // The actual token class is dependent on your spring-security library (OAuth2/JWT/...)
//        readers.add(new BearerAuthenticationReader(BearerTokenAuthenticationToken::new));
//        return new CompositeGrpcAuthenticationReader(readers);
//    }
//
//    @Bean
//    GrpcSecurityMetadataSource grpcSecurityMetadataSource() {
//        final ManualGrpcSecurityMetadataSource source = new ManualGrpcSecurityMetadataSource();
////        source.set(MyServiceGrpc.getMethodA(), AccessPredicate.authenticated());
////        source.set(MyServiceGrpc.getMethodB(), AccessPredicate.hasRole("ROLE_USER"));
////        source.set(MyServiceGrpc.getMethodC(), AccessPredicate.hasAllRoles("ROLE_FOO", "ROLE_BAR"));
////        source.set(MyServiceGrpc.getMethodD(), (auth, call) -> "admin".equals(auth.getName()));
//        source.setDefault(AccessPredicate.denyAll());
//        return source;
//    }
//
//    @Bean
//    AccessDecisionManager accessDecisionManager() {
//        final List<AccessDecisionVoter<?>> voters = new ArrayList<>();
//        voters.add(new AccessPredicateVoter());
//        return new UnanimousBased(voters);
//    }
//}
