package zingmp3.hcmus.playlistservice.service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.dto.SongDTO;

public interface SongService {
    Flux<SongDTO> findAllSongByPlaylistId(String playlistId, Pageable pageable);
    Mono<SongDTO> save(SongDTO songDTO, String playlistId);
}
