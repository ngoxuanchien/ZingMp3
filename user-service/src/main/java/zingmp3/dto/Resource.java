package zingmp3.dto;

import org.keycloak.representations.idm.authorization.ScopeRepresentation;

import java.util.Set;

public class Resource {
    private String id;
    private  String name;
    private Set<ScopeRepresentation> scopes;
    private Set<String> URIs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getURIs() {
        return URIs;
    }

    public void setURIs(Set<String> URIs) {
        this.URIs = URIs;
    }

    public Set<ScopeRepresentation> getScopes() {
        return scopes;
    }

    public void setScopes(Set<ScopeRepresentation> scopes) {
        this.scopes = scopes;
    }
}
