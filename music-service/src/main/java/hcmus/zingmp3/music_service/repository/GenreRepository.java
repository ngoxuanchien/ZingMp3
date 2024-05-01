package hcmus.zingmp3.music_service.repository;

import hcmus.zingmp3.music_service.entity.GenreEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.nio.channels.FileChannel;

@Repository
public interface GenreRepository extends ReactiveCrudRepository<GenreEntity, String> {
    Mono<GenreEntity> findByAlias(String alias);
}
