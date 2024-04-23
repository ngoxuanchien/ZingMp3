package zingmp3.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zingmp3.service.keycloak.RoleService;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class KeycloakRoleController {
    private final RoleService roleService;

    @PutMapping("/assign-role/user/{userId}")
    public ResponseEntity<?> assignRole(@PathVariable String userId, @RequestParam String roleName){

        roleService.assignRole(userId, roleName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
