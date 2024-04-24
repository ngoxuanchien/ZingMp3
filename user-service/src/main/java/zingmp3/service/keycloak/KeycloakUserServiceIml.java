package zingmp3.service.keycloak;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import zingmp3.dto.UserRegistrationRecord;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeycloakUserServiceIml implements KeycloakUserService {
    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    private final Keycloak keycloak;


    @Override
    public Response createUser(UserRegistrationRecord userRegistrationRecord) {
        return null;
    }

    private void assignRole(String userId, String role) {
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

    private UsersResource getUsersResource() {
        RealmResource realmResource = keycloak.realm(realm);
        return realmResource.users();
    }

    @Override
    public UserRepresentation getUserById(String userId) {
        return getUsersResource().get(userId).toRepresentation();
    }

    @Override
    public void deleteUserById(String userId) {
        try (Response delete = getUsersResource().delete(userId)) {
            System.out.println(delete);
            log.info("Delete user with id: {}", userId);
        }
    }

    @Override
    public UserResource getUserResource(String userId) {
        UsersResource userResource = getUsersResource();
        return userResource.get(userId);
    }

    @Override
    public UserRegistrationRecord register(UserRegistrationRecord newUser) {
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

        UsersResource usersResource = getUsersResource();

        Response response = usersResource.create(user);
        log.info(String.valueOf(response.getStatus()));

        if (Objects.equals(response.getStatus(), 201)) {
            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            assignRole(userId, "user");
            return newUser;
        } else {
            throw new RuntimeException("Error creating user");
        }

//        return response;
    }
}
