package zingmp3.hcmus.playlistservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.dto.SongDTO;
import zingmp3.hcmus.playlistservice.entity.PlaylistSongEntity;
import zingmp3.hcmus.playlistservice.exception.PlaylistNotFoundException;
import zingmp3.hcmus.playlistservice.repository.PlaylistRepository;
import zingmp3.hcmus.playlistservice.repository.PlaylistSongRepository;
import zingmp3.hcmus.playlistservice.service.SongService;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final PlaylistSongRepository playlistSongRepository;
    private final PlaylistRepository playlistRepository;
    private final WebClient.Builder webClient;

    private Mono<SongDTO> getSongById(String id) {
        return webClient
                .build()
                .get()
                .uri("http://music-service/api/music/song/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(SongDTO.class);
    }

    private Mono<SongDTO> getSongByAlias(String alias) {
        return webClient
                .build()
                .get()
                .uri("http://music-service/api/music/song/alias/{alias}", alias)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(SongDTO.class);
    }

    private Mono<SongDTO> createSong(SongDTO songDTO) {
        return webClient
                .build()
                .post()
                .uri("http://music-service/api/music/song")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(songDTO)
                .retrieve()
                .bodyToMono(SongDTO.class)
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.empty();
                });
    }

    @Override
    public Flux<SongDTO> findAllSongByPlaylistId(String playlistId, Pageable pageable) {
        return playlistSongRepository.findAllByPlaylistId(playlistId, pageable)
                .flatMap(playlistSongEntity -> getSongById(playlistSongEntity.getSongId())
                        .onErrorResume(e -> {
                            e.printStackTrace();
                            return Mono.empty();
                        }))
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Mono<SongDTO> save(SongDTO songDTO, String playlistId) {
        return getSongByAlias(songDTO.getAlias())
                .switchIfEmpty(createSong(songDTO))
                .flatMap(song -> playlistSongRepository.save(
                        PlaylistSongEntity.builder()
                                .songId(song.getId())
                                .playlistId(playlistId)
                                .build()
                                .setAsNew()
                ))
                .flatMap(entity -> getSongById(entity.getSongId()));
    }
}
