package zingmp3.service.keycloak;

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
import zingmp3.domain.exception.EmailExistException;
import zingmp3.domain.exception.ForbiddenException;
import zingmp3.domain.exception.UnauthorizedException;

import javax.annotation.Nonnull;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakServiceImpl implements KeycloakService {

    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.urls.auth}")
    private String serverUrl;

    private Keycloak initKeycloak(String clientId, String clientSecret) {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }

    private static UserRepresentation getUserRepresentation(String email, String name, String password) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setEmail(email);
        user.setAttributes(new AbstractMap<>() {
            @Override
            public @Nonnull Set<Entry<String, List<String>>> entrySet() {
                Set<Entry<String, List<String>>> set = new HashSet<>();
                set.add(new SimpleEntry<>("name", List.of(name)));
                return set;
            }
        });
        user.setEmailVerified(false);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setValue(password);
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);

        user.setCredentials(List.of(credential));
        return user;
    }

    private void assignRole(Keycloak keycloak, String userId, String role) {
        List<RoleRepresentation> roleToAdd = new LinkedList<>();
        roleToAdd.add(
                keycloak.realm(realm)
                        .roles()
                        .get(role)
                        .toRepresentation());

        keycloak.realm(realm)
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(roleToAdd);
    }

    @Override
    public String createDistributor(String email, String name, String password) {

        var distributor = getUserRepresentation(email, name, password);

        try {
            Response response = keycloak
                    .realm(realm)
                    .users()
                    .create(distributor);

            switch (response.getStatus()) {
                case 201:
                    String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
                    assignRole(keycloak, userId, "distributor");
                    return userId;
                case 409:
                    log.error(String.valueOf(response.getStatus()));
                    log.error(response.toString());
                    throw new EmailExistException("Email already exists!");
                default:
                    log.info(String.valueOf(response.getStatus()));
                    throw new ForbiddenException("Cannot register user! Try again later");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new UnauthorizedException("Cannot connect to Keycloak server");
        }
    }

    @Override
    public void createUser(String email, String name, String password, String clientId, String clientSecret) {
        var keycloak = initKeycloak(clientId, clientSecret);

        UserRepresentation user = getUserRepresentation(email, name, password);

        try {
            Response response = keycloak
                    .realm(realm)
                    .users()
                    .create(user);

            switch (response.getStatus()) {
                case 201:
                    String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
                    assignRole(keycloak, userId, "user");
                    return;
                case 409:
                    log.error(String.valueOf(response.getStatus()));
                    log.error(response.toString());
                    throw new EmailExistException("Email already exists!");
                default:
                    log.info(String.valueOf(response.getStatus()));
                    throw new ForbiddenException("Cannot register user! Try again later");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new UnauthorizedException("Cannot connect to Keycloak server");
        }
    }

    @Override
    public UUID getUserId(String email) {
        var userId = keycloak.realm(realm)
                .users()
                .search(email)
                .getFirst()
                .getId();
        return UUID.fromString(userId);
    }

    @Override
    public void deleteUser(UUID id) {
        try {
            keycloak.realm(realm)
                    .users()
                    .delete(id.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void setNewPassword(String userId, String newPassword) {
        try {
            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setValue(newPassword);
            credential.setTemporary(false);
            credential.setType(CredentialRepresentation.PASSWORD);

            keycloak.realm(realm)
                    .users()
                    .get(userId)
                    .resetPassword(credential);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}
