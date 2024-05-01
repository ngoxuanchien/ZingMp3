package zingmp3.hcmus.playlistservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.entity.PlaylistGenreEntity;

import java.nio.channels.FileChannel;

@Repository
public interface PlaylistGenreRepository extends ReactiveCrudRepository<PlaylistGenreEntity, String> {
    Flux<PlaylistGenreEntity> findAllByPlaylistId(String playlistId);

    Mono<Void> deleteByPlaylistIdAndGenreId(String playlistId, String genreId);

    Mono<Void> deleteAllByPlaylistId(String playlistId);
}
