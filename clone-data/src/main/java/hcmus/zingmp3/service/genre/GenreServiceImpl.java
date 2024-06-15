package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.dto.genre.GenreRequest;
import hcmus.zingmp3.dto.genre.GenreResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static hcmus.zingmp3.Main.user;

@Service
public class GenreServiceImpl implements GenreService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public GenreResponse createGenre(GenreRequest genreRequest) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/genres";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(user.accessToken());

            HttpEntity<GenreRequest> requestEntity = new HttpEntity<>(genreRequest, headers);

            ResponseEntity<GenreResponse> response = restTemplate.postForEntity(url, requestEntity, GenreResponse.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                return response.getBody();
            } else {
                throw new RuntimeException("Failed to create genre");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create genre", e);
        }
    }

    @Override
    public void deleteGenre(UUID genreId) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/genres/" + genreId.toString();

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(user.accessToken());

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);

            if (response.getStatusCode() != HttpStatus.NO_CONTENT) {
                throw new RuntimeException("Failed to delete genre");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
