package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.dto.genre.GenreRequest;
import hcmus.zingmp3.dto.genre.GenreResponse;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static hcmus.zingmp3.Main.restTemplate;
import static hcmus.zingmp3.Main.user;

@Service
public class GenreServiceImpl implements GenreService {

    @Override
    public GenreResponse createGenre(GenreRequest genreRequest) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/genres";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(user.accessToken());

            HttpEntity<GenreRequest> requestEntity = new HttpEntity<>(genreRequest, headers);

            ResponseEntity<GenreResponse> response = restTemplate.postForEntity(url, requestEntity, GenreResponse.class);

            return response.getBody();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
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
            e.printStackTrace();
        }
    }

    @Override
    public boolean isGenreExist(String genreAlias) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/genres?alias=" + genreAlias;

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(user.accessToken());

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("Genre with alias " + genreAlias + " not found");
                return false;
            } else {
                throw new RuntimeException("Failed to check if genre exists", e);
            }
        }
    }

    @Override
    public GenreResponse getOrCreateIfNotExist(GenreRequest genreRequest) {
        GenreResponse response = getGenreByAlias(genreRequest.alias());

        if (response != null) {
            return response;
        }

        return createGenre(genreRequest);
    }

    @Override
    public GenreResponse getGenreByAlias(String genreAlias) {
        try {
            String url = "http://nxc-hcmus.me:8081/api/genres?alias=" + genreAlias;

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(user.accessToken());

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<GenreResponse> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, GenreResponse.class);

            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("Genre with alias " + genreAlias + " not found");
                return null;
            } else {
                throw new RuntimeException("Failed to check if genre exists", e);
            }
        }
    }
}
