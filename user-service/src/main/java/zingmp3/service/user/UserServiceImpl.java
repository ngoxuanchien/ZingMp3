package zingmp3.service.user;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import zingmp3.service.keycloak.KeycloakService;
import zingmp3.web.dto.UserRequest;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;

    private final KeycloakService keycloakService;


    @Override
    public UserRepresentation getById(String id) {
        return keycloak.realm(realm)
                .users()
                .get(id)
                .toRepresentation();
    }

    @Override
    public void register(UserRequest request) {
        keycloakService.createUser(
                request.email(),
                request.name(),
                request.password(),
                request.clientId(),
                request.clientSecret()
        );
    }

    @Override
    public void changePassword(String newPassword) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        keycloakService.setNewPassword(userId, newPassword);
    }
}
