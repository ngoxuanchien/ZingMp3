package zingmp3.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.common.util.CollectionUtil;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import zingmp3.config.KeycloakSecurityUtil;
import zingmp3.dto.Role;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/keycloak")
@SecurityRequirement(name="Keycloak")
public class RoleResource {

    @Autowired
    KeycloakSecurityUtil keycloakSecurityUtil;

    @Value("${realm}")
    private String realm;

    @GetMapping(value = "/roles")
    public List<Role> getRoles(){
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        List<RoleRepresentation> roleRepresentationList =
                keycloak.realm(realm).roles().list();
        System.out.println(roleRepresentationList);
        return mapRoles(roleRepresentationList);
    }

    @GetMapping(value = "/roles/{roleName}")
    public Role getRole(@PathVariable("roleName") String roleName){
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        return mapRole(keycloak.realm(realm).roles().get(roleName).toRepresentation());
    }

    @PostMapping(value = "/role")
    public Response createRole(Role role){
        RoleRepresentation roleRep = mapRoleRep(role);
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        keycloak.realm(realm).roles().create(roleRep);
        return Response.ok(role).build();
    }
    @PutMapping(value = "/role")
    public Response updateRole(Role role){
        RoleRepresentation roleRep = mapRoleRep(role);
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        keycloak.realm(realm).roles().get(role.getName()).update(roleRep);
        return Response.ok(role).build();
    }

    @DeleteMapping(value = "/roles/{roleName}")
    public Response deleteRole(@PathVariable("roleName") String roleName){
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        keycloak.realm(realm).roles().deleteRole(roleName);
        return Response.ok().build();
    }

    public static List<Role> mapRoles(List<RoleRepresentation> representations){
        List<Role> roles = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(representations)){
            representations.forEach(roleRep -> roles.add(mapRole(roleRep)));
        }
        return roles;
    }

    public static Role mapRole(RoleRepresentation roleRep){
        Role role = new Role();
        role.setId(roleRep.getId());
        role.setName(roleRep.getName());
        return role;
    }

    public RoleRepresentation mapRoleRep(Role role){
        RoleRepresentation roleRep = new RoleRepresentation();
        roleRep.setName(role.getName());
        return roleRep;
    }

}
