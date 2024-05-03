package hcmus.zingmp3.music_service.service;

import hcmus.zingmp3.music_service.dto.SongDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface SongService {
    Mono<SongDTO> create(SongDTO songDTO);
    Mono<SongDTO> findById(String id);
    Mono<SongDTO> findByAlias(String alias);
    Mono<Boolean> existsById(String id);

    Flux<SongDTO> searchSongs(String keyword, Pageable pageable);
}
