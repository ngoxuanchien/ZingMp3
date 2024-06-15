package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.dto.ErrorMessage;
import hcmus.zingmp3.dto.artist.ArtistRequest;
import hcmus.zingmp3.dto.artist.ArtistResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static hcmus.zingmp3.Main.gson;
import static hcmus.zingmp3.Main.user;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public UUID createArtist(ArtistRequest artistRequest) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/artists";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(user.accessToken());

            HttpEntity<ArtistRequest> requestEntity = new HttpEntity<>(artistRequest, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                ArtistResponse artistResponse = gson.fromJson(response.getBody(), ArtistResponse.class);
                return artistResponse.id();
            } else {
                ErrorMessage errorMessage = gson.fromJson(response.getBody(), ErrorMessage.class);
                System.out.println("Error: " + errorMessage.message());
                return null;
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                ErrorMessage errorMessage = gson.fromJson(e.getResponseBodyAsString(), ErrorMessage.class);
                System.out.println("Error: " + errorMessage.message());
                System.out.println("Details: " + errorMessage.errors());
            }
            return null;
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
