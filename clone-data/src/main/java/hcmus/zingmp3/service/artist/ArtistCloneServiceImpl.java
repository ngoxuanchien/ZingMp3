package hcmus.zingmp3.service.artist;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import hcmus.zingmp3.dto.artist.ArtistRequest;
import hcmus.zingmp3.dto.artist.ArtistResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static hcmus.zingmp3.Main.*;

public class ArtistCloneServiceImpl implements ArtistCloneService {
    @Override
    public UUID cloneArtist(JsonObject jsonObject) {
        ArtistRequest request = artistMapper.toRequest(jsonObject);

        ArtistResponse response = artistService.getOrCreateIfNotExist(request);

        return response.id();
    }

    @Override
    public List<UUID> cloneArtist(JsonArray jsonArray) {
        List<UUID> result = new ArrayList<>();
        jsonArray.forEach(
                jsonElement ->
                        result.add(cloneArtist(jsonElement.getAsJsonObject().get("alias").getAsString()))
        );

        return result;
    }

    @Override
    public UUID cloneArtist(String artistAlias) {
        try {
            String url = "http://localhost:3000/test/getArtist/" + artistAlias;

            HttpHeaders headers = new HttpHeaders();

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

            JsonObject jsonObject = gson.fromJson(response.getBody(), JsonObject.class);
            return cloneArtist(jsonObject);


        } catch (HttpClientErrorException e) {
            throw e;
        }
    }
}
