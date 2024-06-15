package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.dto.ErrorMessage;
import hcmus.zingmp3.dto.artist.ArtistRequest;
import hcmus.zingmp3.dto.artist.ArtistResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static hcmus.zingmp3.Main.*;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Override
    public ArtistResponse createArtist(ArtistRequest artistRequest) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/artists";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(user.accessToken());

            HttpEntity<ArtistRequest> requestEntity = new HttpEntity<>(artistRequest, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                ArtistResponse artistResponse = gson.fromJson(response.getBody(), ArtistResponse.class);
                return artistResponse;
            } else {
                ErrorMessage errorMessage = gson.fromJson(response.getBody(), ErrorMessage.class);
                System.out.println("Error: " + errorMessage.message());
                return null;
            }
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create artist", e);
        }
    }

    @Override
    public ArtistResponse getOrCreateIfNotExist(ArtistRequest artistRequest) {
        ArtistResponse response = getArtistByAlias(artistRequest.alias());

        if (response != null) {
            return response;
        }

        return createArtist(artistRequest);
    }

    @Override
    public ArtistResponse getArtistByAlias(String artistAlias) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/artists?alias=" + artistAlias;

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(user.accessToken());

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<ArtistResponse> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ArtistResponse.class);

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
    public void deleteArtist(UUID artistId) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/artists/" + artistId.toString();

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(user.accessToken());

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);

            if (response.getStatusCode() != HttpStatus.NO_CONTENT) {
                throw new RuntimeException("Failed to delete artist");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Error: " + e.getStatusCode() + " - " + e.getStatusText());
            System.out.println("Response Body: " + e.getResponseBodyAsString());
        }
    }
}
