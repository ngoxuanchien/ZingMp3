package zingmp3.hcmus.playlistservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import zingmp3.hcmus.playlistservice.entity.PlaylistArtistEntity;

@Repository
public interface PlaylistArtistRepository extends ReactiveCrudRepository<PlaylistArtistEntity, String> {
    Flux<PlaylistArtistEntity> findAllByPlaylistId(String id);
}
