package zingmp3.hcmus.playlistservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.entity.PlaylistEntity;

import java.util.UUID;

@Repository
public interface PlaylistRepository extends ReactiveCrudRepository<PlaylistEntity, String> {
    Flux<PlaylistEntity> findAllBy(Pageable pageable);
}
