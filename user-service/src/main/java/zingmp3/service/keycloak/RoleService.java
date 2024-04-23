package zingmp3.service.keycloak;

public interface RoleService {

    void assignRole(String userId, String roleName);
}
