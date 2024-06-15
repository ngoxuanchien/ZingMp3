package hcmus.zingmp3.service.media;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.UUID;

import static hcmus.zingmp3.Main.user;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson;

    @Override
    public UUID uploadMedia(String path) {
        String url = "http://nxc-hcmus.me:8081/api/audios";

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        Resource resource = new FileSystemResource(new File(path));
        body.add("audio", resource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setBearerAuth(user.accessToken());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();

            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);

            UUID mediaId = UUID.fromString(jsonObject.get("id").getAsString());
            return mediaId;
        } else {
            throw new RuntimeException("Failed to upload media");
        }
    }
}
