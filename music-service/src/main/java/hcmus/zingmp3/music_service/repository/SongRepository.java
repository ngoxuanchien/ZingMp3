package hcmus.zingmp3.music_service.repository;

import hcmus.zingmp3.music_service.entity.SongEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.nio.channels.FileChannel;

@Repository
public interface SongRepository extends ReactiveCrudRepository<SongEntity, String> {
    Mono<SongEntity> findByAlias(String alias);
}
