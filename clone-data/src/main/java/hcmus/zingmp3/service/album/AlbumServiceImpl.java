package hcmus.zingmp3.service.album;

import hcmus.zingmp3.dto.album.AlbumRequest;
import hcmus.zingmp3.dto.album.AlbumResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static hcmus.zingmp3.Main.restTemplate;
import static hcmus.zingmp3.Main.user;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Override
    public AlbumResponse createAlbum(AlbumRequest albumRequest) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/albums";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(user.accessToken());

            HttpEntity<AlbumRequest> requestEntity = new HttpEntity<>(albumRequest, headers);

            ResponseEntity<AlbumResponse> response = restTemplate.postForEntity(url, requestEntity, AlbumResponse.class);

            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw e;
        }
    }

    @Override
    public AlbumResponse getOrCreateIfNotExist(AlbumRequest albumRequest) {

        AlbumResponse response = getAlbumByAlias(albumRequest.alias());

        if (response != null) {
            return response;
        }

        return createAlbum(albumRequest);
    }

    @Override
    public AlbumResponse getAlbumByAlias(String albumAlias) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/albums?alias=" + albumAlias;

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(user.accessToken());

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<AlbumResponse> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, AlbumResponse.class);

            return response.getBody();

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            } else {
                throw e;
            }
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
