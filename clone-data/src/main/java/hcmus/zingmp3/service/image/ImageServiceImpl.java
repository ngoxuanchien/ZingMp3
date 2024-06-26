package hcmus.zingmp3.service.image;

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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

import static hcmus.zingmp3.Main.*;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Override
    public UUID uploadImage(String path) {
        String url = "http://nxc-hcmus.me:8081/api/images?replace=true";

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        Resource resource = new FileSystemResource(new File(path));
        body.add("image", resource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setBearerAuth(user.accessToken());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            String responseBody = response.getBody();

            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);

            UUID imageId = UUID.fromString(jsonObject.get("id").getAsString());
            return imageId;
        } else {
            throw new RuntimeException("Failed to upload image");
        }
    }
}
