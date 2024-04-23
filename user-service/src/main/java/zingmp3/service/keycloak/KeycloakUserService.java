package zingmp3.service.keycloak;

import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import zingmp3.dto.UserRegistrationRecord;

public interface KeycloakUserService {
    Response createUser(UserRegistrationRecord userRegistrationRecord);
    UserRepresentation getUserById(String userId);
    void deleteUserById(String userId);

    UserResource getUserResource(String userId);

    UserRegistrationRecord register(UserRegistrationRecord newUser);
}
