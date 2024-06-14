package zingmp3.service.keycloak;

import java.util.UUID;

public interface KeycloakService {
    String createDistributor(String email, String name, String password);
    void createUser(String email, String name, String password, String clientId, String clientSecret);
    UUID getUserId(String email);

    void deleteUser(UUID id);

    void setNewPassword(String userId, String newPassword);
}
