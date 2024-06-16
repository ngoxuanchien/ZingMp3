package hcmus.zingmp3.service.playlist;

import hcmus.zingmp3.dto.playlist.PlaylistRequest;
import hcmus.zingmp3.dto.playlist.PlaylistResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

import static hcmus.zingmp3.Main.restTemplate;
import static hcmus.zingmp3.Main.user;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Override
    public PlaylistResponse createPlaylist(PlaylistRequest playlistRequest) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/playlists";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(user.accessToken());

            HttpEntity<PlaylistRequest> requestEntity = new HttpEntity<>(playlistRequest, headers);

            ResponseEntity<PlaylistResponse> response = restTemplate.postForEntity(url, requestEntity, PlaylistResponse.class);

            System.out.println("Create playlist: " + response.getBody().id());

            return response.getBody();

        } catch (HttpClientErrorException e) {
            throw e;
        }
    }

    @Override
    public PlaylistResponse getOrCreateIfNotExist(PlaylistRequest playlistRequest) {
        PlaylistResponse response = getPlaylistByAlias(playlistRequest.alias());

        if (response != null) {
            return  response;
        }

        return createPlaylist(playlistRequest);
    }

    @Override
    public PlaylistResponse getPlaylistByAlias(String playlistAlias) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/playlists?alias=" + playlistAlias;

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(user.accessToken());

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<PlaylistResponse> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, PlaylistResponse.class);

            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }
            throw e;
        }
    }

    @Override
    public void deletePlaylist(UUID playlistId) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/playlists/" + playlistId.toString();

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(user.accessToken());

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);

            if (response.getStatusCode() != HttpStatus.NO_CONTENT) {
                throw new RuntimeException("Failed to delete playlist");
            }
        } catch (HttpClientErrorException e) {
            throw e;
        }
    }
}
