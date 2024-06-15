package hcmus.zingmp3.service.song;

import hcmus.zingmp3.dto.ErrorMessage;
import hcmus.zingmp3.dto.artist.ArtistResponse;
import hcmus.zingmp3.dto.song.SongRequest;
import hcmus.zingmp3.dto.song.SongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static hcmus.zingmp3.Main.*;

@Service
public class SongServiceImpl implements SongService {

    @Override
    public SongResponse createSong(SongRequest songRequest) {
        if (songRequest == null) {
            return null;
        }

        try {
            String url = "http://nxc-hcmus.me:8081/api/songs";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(user.accessToken());

            HttpEntity<SongRequest> requestEntity = new HttpEntity<>(songRequest, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                SongResponse songResponse = gson.fromJson(response.getBody(), SongResponse.class);
                return songResponse;
            } else {
                ErrorMessage errorMessage = gson.fromJson(response.getBody(), ErrorMessage.class);
                System.out.println("Error: " + errorMessage.message());
                return null;
            }
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create song", e);
        }
    }

    @Override
    public SongResponse getOrCreateIfNotExist(SongRequest songRequest) {
        SongResponse response = getSongByAlias(songRequest.alias());

        if (response != null) {
            return response;
        }

        return createSong(songRequest);
    }

    @Override
    public SongResponse getSongByAlias(String songAlias) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/songs?alias=" + songAlias;

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(user.accessToken());

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<SongResponse> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, SongResponse.class);

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
    public void deleteSong(UUID songId) {
        if (songId == null) {
            return;
        }

        try {
            String url = "http://nxc-hcmus.me:8081/api/songs/" + songId.toString();

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(user.accessToken());

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);

            if (response.getStatusCode() != HttpStatus.NO_CONTENT) {
                throw new RuntimeException("Failed to delete song");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Error: " + e.getStatusCode() + " - " + e.getStatusText());
            e.printStackTrace();
            System.out.println("Response Body: " + e.getResponseBodyAsString());
        }
    }
}
