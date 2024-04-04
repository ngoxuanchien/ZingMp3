package zingmp3.service;

import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import zingmp3.dto.AuthenticatedRequest;
import zingmp3.dto.UserDto;

@Service
public class UserService {
    public AccessTokenResponse authenticate(AuthenticatedRequest request) {
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

    public int register(UserDto request) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://192.168.1.21:8080")
                .realm("zing-mp3")
                .grantType(OAuth2Constants.PASSWORD)
                .clientId("zing-mp3-api")
                .clientSecret("VmOLegqJIaQq1ppbKtjNPOm3J1jOL1n3")
                .username("nxc")
                .password("nxc")
                .build();

        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        System.out.println(user);

        RealmResource realmResource = keycloak.realm("zing-mp3");
        UsersResource usersResource = realmResource.users();

        Response response = usersResource.create(user);
        System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());
        System.out.println(response.getLocation());

        if (response.getStatus() == 201) {
            String userId = CreatedResponseUtil.getCreatedId(response);

            System.out.printf("User created with userId: %s%n", userId);

            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(request.getPassword());
            passwordCred.setTemporary(false);

            UserResource userResource = usersResource.get(userId);

            userResource.resetPassword(passwordCred);

        }
        return response.getStatus();
    }
}
