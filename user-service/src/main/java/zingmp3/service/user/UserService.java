package zingmp3.service.user;

import org.keycloak.representations.idm.UserRepresentation;
import zingmp3.web.dto.UserRequest;

public interface UserService {
    UserRepresentation getById(String id);

    void register(UserRequest request);

    void changePassword(String newPassword);
}
