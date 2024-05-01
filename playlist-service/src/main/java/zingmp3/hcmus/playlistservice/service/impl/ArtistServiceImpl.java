package zingmp3.hcmus.playlistservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.dto.ArtistDTO;
import zingmp3.hcmus.playlistservice.entity.PlaylistArtistEntity;
import zingmp3.hcmus.playlistservice.repository.PlaylistArtistRepository;
import zingmp3.hcmus.playlistservice.service.ArtistService;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final PlaylistArtistRepository repository;
    private final WebClient.Builder webClient;

    private Mono<ArtistDTO> getArtistById(String id) {
        return webClient
                .build()
                .get()
                .uri("http://music-service/api/music/artist/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ArtistDTO.class);
    }

    private Mono<ArtistDTO> getArtistByAlias(String alias) {
        return webClient
                .build()
                .get()
                .uri("http://music-service/api/music/artist/alias/{alias}", alias)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ArtistDTO.class);
    }

    private Mono<ArtistDTO> createArtist(ArtistDTO artistDTO) {
        return webClient
                .build()
                .post()
                .uri("http://music-service/api/music/artist")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(artistDTO)
                .retrieve()
                .bodyToMono(ArtistDTO.class);
    }

    @Override
    public Flux<ArtistDTO> findAllArtistsByPlaylistId(String id) {
        return repository.findAllByPlaylistId(id)
                .flatMap(entity -> {
                    return getArtistById(entity.getArtistId())
                            .onErrorResume(e -> {
                                e.printStackTrace();
                                return Mono.empty();
                            });
                }).doOnError(Throwable::printStackTrace);
    }

    @Override
    public Mono<ArtistDTO> save(ArtistDTO artistDTO, String playlistId) {
        return getArtistByAlias(artistDTO.getAlias())
                .switchIfEmpty(createArtist(artistDTO))
                .flatMap(artist -> repository.save(
                        PlaylistArtistEntity.builder()
                                .artistId(artist.getId())
                                .playlistId(playlistId)
                                .build()
                                .setAsNew()
                ))
                .flatMap(entity -> getArtistById(entity.getArtistId()));
    }


}
