package hcmus.zingmp3.music_service.repository;

import hcmus.zingmp3.music_service.entity.SongComposerEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SongComposerRepository extends ReactiveCrudRepository<SongComposerEntity, String> {
    Flux<SongComposerEntity> findAllBySongId(String songId);
}
