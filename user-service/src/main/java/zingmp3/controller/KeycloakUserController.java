package zingmp3.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zingmp3.keycloak.KeycloakUserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class KeycloakUserController {
    private final KeycloakUserService keycloakUserService;
}
