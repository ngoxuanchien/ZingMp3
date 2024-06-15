package hcmus.zingmp3.service.album;

import hcmus.zingmp3.dto.album.AlbumRequest;
import hcmus.zingmp3.dto.album.AlbumResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static hcmus.zingmp3.Main.user;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public AlbumResponse createAlbum(AlbumRequest albumRequest) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/albums";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(user.accessToken());

            HttpEntity<AlbumRequest> requestEntity = new HttpEntity<>(albumRequest, headers);

            ResponseEntity<AlbumResponse> response = restTemplate.postForEntity(url, requestEntity, AlbumResponse.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                return response.getBody();
            } else {
                throw new RuntimeException("Failed to create album");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create album", e);
        }
    }

    @Override
    public void deleteAlbum(UUID albumId) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/albums/" + albumId.toString();

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(user.accessToken());

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);

            if (response.getStatusCode() != HttpStatus.NO_CONTENT) {
                throw new RuntimeException("Failed to delete album");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete album", e);
        }
    }
}