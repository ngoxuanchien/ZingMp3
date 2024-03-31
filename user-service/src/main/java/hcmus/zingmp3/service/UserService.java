package hcmus.zingmp3.service;

import hcmus.zingmp3.dto.AuthenticationRequest;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public AccessTokenResponse authenticate(AuthenticationRequest request) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://192.168.1.21:8080")
                .realm("zing-mp3")
                .grantType(OAuth2Constants.PASSWORD)
                .clientId("zing-mp3-api")
                .clientSecret("VmOLegqJIaQq1ppbKtjNPOm3J1jOL1n3")
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        return keycloak.tokenManager().getAccessToken();
    }
}
