package hcmus.zingmp3.service.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.dto.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static hcmus.zingmp3.Main.*;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getAccessToken(String username, String password) {
        String keycloakUrl = "http://nxc-hcmus.me:8081/api/auth/token";
        String clientId = "user-service";
        String clientSecret = "jSLIfcd5eq2t6e0CzNid3QKUaQNP1m0x";

        MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<>();
        parametersMap.add("grant_type", "password");
        parametersMap.add("client_id", clientId);
        parametersMap.add("client_secret", clientSecret);
        parametersMap.add("username", username);
        parametersMap.add("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parametersMap, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(keycloakUrl, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            // Parse the response body to get the access token
            String responseBody = response.getBody();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            return  new User(
                    jsonObject.get("access_token").getAsString(),
                    jsonObject.get("refresh_token").getAsString()
            );
        } else {
            throw new RuntimeException("Failed to get access token");
        }
    }

    @Override
    public void logout() {
        String keycloakUrl = "http://nxc-hcmus.me:8081/api/auth/logout";
        String clientId = "user-service";
        String clientSecret = "jSLIfcd5eq2t6e0CzNid3QKUaQNP1m0x";

        MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<>();
        parametersMap.add("client_id", clientId);
        parametersMap.add("client_secret", clientSecret);
        parametersMap.add("refresh_token", user.refreshToken());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parametersMap, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(keycloakUrl, request, String.class);

        if (response.getStatusCode() != HttpStatus.NO_CONTENT) {
            throw new RuntimeException("Failed to logout");
        }
    }
}
