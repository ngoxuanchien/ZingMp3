package zingmp3.service.keycloak;

public interface KeycloakService {
    String createDistributor(String email, String name, String password, String clientId, String clientSecret);
    void createUser(String email, String name, String password, String clientId, String clientSecret);
}
