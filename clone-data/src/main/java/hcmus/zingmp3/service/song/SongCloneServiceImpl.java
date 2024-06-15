package hcmus.zingmp3.service.song;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import hcmus.zingmp3.dto.song.SongRequest;
import hcmus.zingmp3.dto.song.SongResponse;
import hcmus.zingmp3.mapper.SongMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static hcmus.zingmp3.Main.gson;
import static hcmus.zingmp3.Main.restTemplate;

@Service
public class SongCloneServiceImpl implements SongCloneService {

    private static final SongMapper songMapper = new SongMapper();
    private static final SongService songService = new SongServiceImpl();

    @Override
    public SongRequest cloneSong(SongRequest songRequest) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/songs";



        } catch (HttpClientErrorException e) {
            throw e;
        }
        return null;
    }

    @Override
    public List<UUID> cloneSong(JsonArray jsonArray) {
        List<UUID> result = new ArrayList<>();
        jsonArray.asList().forEach(
                jsonElement ->
                        result.add(cloneSong(jsonElement.getAsJsonObject()))
        );
        return result;
    }

    @Override
    public UUID cloneSong(JsonObject jsonObject) {
        SongRequest request = songMapper.toRequest(jsonObject);

        SongResponse response = songService.createSong(request);


        return response.id();
    }

    @Override
    public void cloneSong(String songId) {
        try {
            String url = "http://localhost:3000/test/getFullInfo/" + songId;

            HttpHeaders headers = new HttpHeaders();

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

            JsonObject jsonObject = gson.fromJson(response.getBody(), JsonObject.class);
            cloneSong(jsonObject);
        } catch (HttpClientErrorException e) {
            throw e;
        }
    }
}
