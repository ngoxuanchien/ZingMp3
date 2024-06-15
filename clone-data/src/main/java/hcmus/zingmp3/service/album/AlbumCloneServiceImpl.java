package hcmus.zingmp3.service.album;

import com.google.gson.JsonObject;
import hcmus.zingmp3.dto.album.AlbumRequest;
import hcmus.zingmp3.dto.album.AlbumResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

import static hcmus.zingmp3.Main.*;

public class AlbumCloneServiceImpl implements AlbumCloneService {
    @Override
    public UUID cloneAlbum(JsonObject jsonObject) {
        AlbumRequest request = albumMapper.toRequest(jsonObject);

        AlbumResponse response = albumService.getOrCreateIfNotExist(request);

        return response.id();
    }

    @Override
    public void cloneAlbum(String albumId) {
        try {
            String url = "http://localhost:3000/test/getDetailPlaylist/" + albumId;

            HttpHeaders headers = new HttpHeaders();

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

            JsonObject jsonObject = gson.fromJson(response.getBody(), JsonObject.class);
            cloneAlbum(jsonObject);
        } catch (HttpClientErrorException e) {
            throw e;
        }
    }
}
