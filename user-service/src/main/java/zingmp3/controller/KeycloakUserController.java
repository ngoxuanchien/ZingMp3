package zingmp3.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.*;
import zingmp3.dto.UserRegistrationRequest;
import zingmp3.service.keycloak.KeycloakUserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class KeycloakUserController {
    private final KeycloakUserService keycloakUserService;
}
