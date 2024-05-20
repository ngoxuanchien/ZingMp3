package zingmp3.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.common.util.CollectionUtil;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import zingmp3.config.KeycloakSecurityUtil;
import zingmp3.dto.Role;
import zingmp3.dto.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/keycloak")
@SecurityRequirement(name="Keycloak")
public class UserResource {
    @Autowired
    KeycloakSecurityUtil keycloakSecurityUtil;

    @Value("${realm}")
    private String realm;

    @GetMapping
    @RequestMapping("/users")
    public List<User> getUsers(){
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        List<UserRepresentation> userRepresentations =
                keycloak.realm(realm).users().list();
        return mapUsers(userRepresentations);
    }

    @GetMapping(value = "users/{id}")
    public User getUser(@PathVariable("id") String id){
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        return mapUser(keycloak.realm(realm).users().get(id).toRepresentation());
    }

    @PostMapping("/user")
    public Response createUser(User user){
        UserRepresentation userRep = mapUserRep(user);
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        Response res = keycloak.realm(realm).users().create(userRep);
        user.setId(((UserRepresentation)res.getEntity()).getId());
        return Response.ok(user).build();
    }

    @PutMapping(value = "/user")
    public Response updateUser(User user){
        UserRepresentation userRep = mapUserRep(user);
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        keycloak.realm(realm).users().get(user.getId()).update(userRep);
        return Response.ok(user).build();
    }

    @DeleteMapping(value = "/users/{id}")
    public Response deleteUser(@PathVariable("id") String id){
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        keycloak.realm(realm).users().delete(id);
        return Response.ok().build();
    }

    @GetMapping(value = "/users/{id}/roles")
    public List<Role> getRoles(@PathVariable("id") String id){
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        return RoleResource.mapRoles(keycloak.realm(realm).users()
                .get(id).roles().realmLevel().listAll());
    }

    private UserRepresentation mapUserRep(User user) {
        UserRepresentation userRep = new UserRepresentation();
        userRep.setUsername(user.getUserName());
        userRep.setFirstName(user.getFirstName());
        userRep.setLastName(user.getLastName());
        userRep.setEmail(user.getEmail());
        userRep.setEnabled(true);
        userRep.setEmailVerified(true);
        List<CredentialRepresentation> creds = new ArrayList<>();
        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setTemporary(false);
        cred.setValue(user.getPassword());
        creds.add(cred);
        userRep.setCredentials(creds);
        return userRep;
    }
    private List<User> mapUsers(List<UserRepresentation> userRepresentations) {
        List<User> users =new ArrayList<>();
        if(CollectionUtil.isNotEmpty(userRepresentations)){
            userRepresentations.forEach(userRep -> {
                users.add(mapUser(userRep));
            });
        }
        return users;
    }
    private User mapUser(UserRepresentation userRep) {
        User user = new User();
        user.setId(userRep.getId());
        user.setFirstName(userRep.getFirstName());
        user.setLastName(userRep.getLastName());
        user.setEmail(userRep.getEmail());
        user.setUserName(userRep.getUsername());
        return user;
    }
}
