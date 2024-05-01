package zingmp3.hcmus.playlistservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.dto.GenreDTO;
import zingmp3.hcmus.playlistservice.entity.PlaylistGenreEntity;
import zingmp3.hcmus.playlistservice.repository.PlaylistGenreRepository;
import zingmp3.hcmus.playlistservice.service.GenreService;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final PlaylistGenreRepository repository;
    private final WebClient.Builder webClient;

    public Mono<GenreDTO> getGenreById(String id) {
        return webClient
                .build()
                .get()
                .uri("http://music-service/api/music/genre/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(GenreDTO.class);
    }

    private Mono<GenreDTO> getGenreByAlias(String alias) {
        return webClient
                .build()
                .get()
                .uri("http://music-service/api/music/genre/alias/{alias}", alias)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(GenreDTO.class);
    }

    private Mono<GenreDTO> createGenre(GenreDTO genreDTO) {
        return webClient
                .build()
                .post()
                .uri("http://music-service/api/music/genre")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(genreDTO)
                .retrieve()
                .bodyToMono(GenreDTO.class);
    }

    @Override
    public Flux<GenreDTO> findAllGenresByPlaylistId(String id) {
        return repository.findAllByPlaylistId(id)
                .flatMap(entity -> getGenreById(entity.getGenreId())
                        .onErrorResume(e -> {
                            e.printStackTrace();
                            return Mono.empty();
                        }))
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Mono<GenreDTO> save(GenreDTO genreDTO, String playlistId) {
        return getGenreByAlias(genreDTO.getAlias())
                .switchIfEmpty(createGenre(genreDTO))
                .flatMap(genre -> repository.save(
                        PlaylistGenreEntity.builder()
                                .genreId(genre.getId())
                                .playlistId(playlistId)
                                .build()
                                .setAsNew()
                ))
                .flatMap(entity -> getGenreById(entity.getGenreId()));
    }
}
