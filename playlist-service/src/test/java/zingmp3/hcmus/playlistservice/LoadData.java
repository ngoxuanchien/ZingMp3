package zingmp3.hcmus.playlistservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.client.ClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.dto.PlaylistDTO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class LoadData {

    @Test
    public void loadData() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        File workingDir = new File(".");
        String absolutePath = workingDir.getAbsolutePath();
        System.out.println(absolutePath);

        try (BufferedReader reader = new BufferedReader(new FileReader("../data/playlist.json"))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            PlaylistDTO[] playlists = gson.fromJson(json.toString(), PlaylistDTO[].class);
            List<PlaylistDTO> playlistDTOList = Arrays.asList(playlists);

            System.out.println(playlistDTOList.size());

            WebClient webClient = WebClient.builder().build();

            String tokenUrl = "http://nxc-hcmus.me:8081/api/auth/token";
            String clientId = "user-service";
            String clientSecret = "jSLIfcd5eq2t6e0CzNid3QKUaQNP1m0x";
            String username = "nxc@gmail.com";
            String password = "nxc";


            Mono<TokenResponse> tokenResponse = webClient
                    .post()
                    .uri(tokenUrl)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .bodyValue("client_id=" + clientId + "&client_secret=" + clientSecret + "&grant_type=password&username=" + username + "&password=" + password)
                    .retrieve()
                    .bodyToMono(TokenResponse.class);

            String token = tokenResponse.block().getAccess_token();
            System.out.println(token);

            int count = 0;
            Flux.fromIterable(playlistDTOList)
                    .delayElements(Duration.ofMillis(500)) // Delay of 500ms between requests
                    .flatMap(playlistDTO -> webClient
                            .post()
                            .uri("http://nxc-hcmus.me:8081/api/playlist")
                            .header("Authorization", "Bearer " + token)
                            .accept(MediaType.APPLICATION_JSON)
                            .bodyValue(playlistDTO)
                            .retrieve()
                            .bodyToMono(PlaylistDTO.class))
                    .blockLast();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
