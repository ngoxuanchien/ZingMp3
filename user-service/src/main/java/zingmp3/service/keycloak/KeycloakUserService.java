package zingmp3.service.keycloak;

import jakarta.ws.rs.core.Response;
import org.keycloak.representations.idm.UserRepresentation;
import zingmp3.dto.UserRegistrationRequest;
import zingmp3.exception.UnauthorizedException;

public interface KeycloakUserService {

    void register(UserRegistrationRequest newUser) throws UnauthorizedException;
}
