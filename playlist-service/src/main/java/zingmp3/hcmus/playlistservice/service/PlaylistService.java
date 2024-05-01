package zingmp3.hcmus.playlistservice.service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.dto.PlaylistDTO;
import zingmp3.hcmus.playlistservice.entity.PlaylistEntity;

public interface PlaylistService {
    Flux<PlaylistDTO> findAll(Pageable pageable);
    Mono<PlaylistDTO> findById(String id);
    Mono<PlaylistDTO> create(PlaylistDTO playlistDTO);
    Mono<PlaylistDTO> update(String id, PlaylistDTO playlistDTO);
    Mono<Void> delete(String id);
}
