package zingmp3.controller;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.resource.AuthorizationResource;
import org.keycloak.common.util.CollectionUtil;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import zingmp3.config.KeycloakSecurityUtil;
import zingmp3.dto.Resource;
import zingmp3.dto.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/keycloak")
public class ResourceController {

    @Autowired
    private KeycloakSecurityUtil keycloakSecurityUtil;

    @Value("${client-id-user-service}")
    private String clientId;

    @Value("${realm}")
    private String realm;

    // GET all resources
    @GetMapping("/resources")
    public List<Resource> getResources() {
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        List<ResourceRepresentation> representations=
                keycloak.realm(realm).clients().get(clientId).authorization().resources().resources();
        return mapResources(representations);
    }

    private List<Resource> mapResources(List<ResourceRepresentation> reps) {
        List<Resource> resources = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(reps)){
            reps.forEach(resourceRep -> resources.add(mapResource(resourceRep)));
        }
        return resources;
    }
    public static Resource mapResource(ResourceRepresentation resourceRep){
        Resource resource = new Resource();
        resource.setId(resourceRep.getId());
        resource.setName(resourceRep.getName());
        resource.setURIs(resourceRep.getUris());
        resource.setScopes(new HashSet<>(resourceRep.getScopes()));
        return resource;
    }

}
