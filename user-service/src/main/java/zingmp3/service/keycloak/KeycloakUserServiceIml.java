package zingmp3.service.keycloak;

import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import zingmp3.dto.UserRegistrationRequest;
import zingmp3.exception.ForbiddenException;
import zingmp3.exception.UnauthorizedException;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeycloakUserServiceIml implements KeycloakUserService {
    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.urls.auth}")
    private String serverUrl;


    private void assignRole(Keycloak keycloak, String userId, String role) {
        List<RoleRepresentation> roleToAdd = new LinkedList<>();
        roleToAdd.add(keycloak
                .realm(realm)
                .roles()
                .get(role)
                .toRepresentation());


        keycloak.realm(realm)
                .users()
                .get(userId)
                .roles().realmLevel()
                .add(roleToAdd);
    }

    @Override
    public void register(UserRegistrationRequest newUser) throws UnauthorizedException {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(newUser.getClientId())
                .clientSecret(newUser.getClientSecret())
                .build();


        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setEmail(newUser.getEmail());
        user.setAttributes(new AbstractMap<String, List<String>>() {
            @Override
            public Set<Entry<String, List<String>>> entrySet() {
                Set<Entry<String, List<String>>> set = new HashSet<>();
                set.add(new AbstractMap.SimpleEntry<>("name", List.of(newUser.getName())));
                return set;
            }
        });
        user.setEmailVerified(false);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setValue(newUser.getPassword());
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);

        user.setCredentials(List.of(credential));

        try {
            Response response = keycloak
                    .realm(realm)
                    .users()
                    .create(user);

            if (Objects.equals(response.getStatus(), 201)) {
                String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
                assignRole(keycloak, userId, "user");
            } else {
                throw new ForbiddenException("Cannot register user! Try again later");
            }
        } catch (ForbiddenException e) {
            log.error(e.getMessage());
            throw new ForbiddenException(e.getMessage());
        } catch (ProcessingException e) {
            System.out.println(e.getMessage());
            throw new UnauthorizedException("Client Unauthorized");

        }



//        return response;
    }
}