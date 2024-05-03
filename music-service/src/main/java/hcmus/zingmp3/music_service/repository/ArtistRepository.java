package hcmus.zingmp3.music_service.repository;

import hcmus.zingmp3.music_service.entity.ArtistEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.channels.FileChannel;

@Repository
public interface ArtistRepository extends ReactiveCrudRepository<ArtistEntity, String> {
    Mono<ArtistEntity> findByAlias(String alias);

    Flux<ArtistEntity> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}
