package hcmus.zingmp3.service;

import com.google.gson.JsonObject;
import hcmus.zingmp3.service.album.AlbumCloneService;
import hcmus.zingmp3.service.album.AlbumCloneServiceImpl;
import hcmus.zingmp3.service.playlist.PlaylistCloneService;
import hcmus.zingmp3.service.playlist.PlaylistCloneServiceImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static hcmus.zingmp3.Main.gson;
import static hcmus.zingmp3.Main.restTemplate;

@Service
public class CloneServiceImpl implements CloneService {

    private static final PlaylistCloneService playlistCloneService = new PlaylistCloneServiceImpl();
    private static final AlbumCloneService albumCloneService = new AlbumCloneServiceImpl();

    @Override
    public void clonePlaylist(String id) {
        try {
            String url = "http://localhost:3000/test/getDetailPlaylist/" + id;

            HttpHeaders headers = new HttpHeaders();

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

            JsonObject jsonObject = gson.fromJson(response.getBody(), JsonObject.class);
            if (jsonObject.get("isAlbum").getAsBoolean()) {
                albumCloneService.cloneAlbum(jsonObject);
            } else {
                playlistCloneService.clonePlaylist(jsonObject);
            }
            System.out.println(jsonObject);

        } catch (HttpClientErrorException e) {
            throw e;
        }
    }
}
