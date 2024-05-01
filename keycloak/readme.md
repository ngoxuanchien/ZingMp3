### Run docker compose:
> docker-compose up

### Export the realm setting
1. Open the docker keycloak terminal and run:
> cd /opt/keycloak/bin  
> ./kc.sh export --file /tmp/keycloak.json
2. Open the window terminal and run:
> docker cp keycloak:/tmp/keycloak.json ./realms