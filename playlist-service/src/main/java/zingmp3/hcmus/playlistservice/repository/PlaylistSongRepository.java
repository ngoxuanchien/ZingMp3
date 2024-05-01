package zingmp3.hcmus.playlistservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.entity.PlaylistSongEntity;

@Repository
public interface PlaylistSongRepository extends ReactiveCrudRepository<PlaylistSongEntity, String> {
    Flux<PlaylistSongEntity> findAllByPlaylistId(String playlistId, Pageable pageable);
    Mono<Void> deleteByPlaylistIdAndSongId(String playlistId, String songId);
    Mono<Void> deleteAllByPlaylistId(String playlistId);
}
