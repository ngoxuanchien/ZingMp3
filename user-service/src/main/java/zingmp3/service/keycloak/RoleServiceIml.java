package zingmp3.service.keycloak;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceIml implements RoleService {
    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    private final Keycloak keycloak;

    @Override
    public void assignRole(String userId, String roleName) {
        log.info(String.valueOf(getAllRoles()));
        String client_id = keycloak
                .realm(realm)
                .clients()
                .findByClientId(clientId)
                .getFirst()
                .getId();
        UserResource user = keycloak
                .realm(realm)
                .users()
                .get(userId);
        List<RoleRepresentation> roleToAdd = new LinkedList<>();
        roleToAdd.add(keycloak
                .realm(realm)
                .clients()
                .get(client_id)
                .roles()
                .get(roleName)
                .toRepresentation()
        );
        user.roles().clientLevel(client_id).add(roleToAdd);
    }

    public List<String> getAllRoles() {
        ClientRepresentation clientRep = keycloak
                .realm(realm)
                .clients()
                .findByClientId(clientId)
                .getFirst();
        return keycloak
                .realm(realm)
                .clients()
                .get(clientRep.getId())
                .roles()
                .list()
                .stream()
                .map(RoleRepresentation::getName)
                .collect(Collectors.toList());
    }



    private RolesResource getRolesResource() {
        return keycloak.realm(realm).roles();
    }
}
