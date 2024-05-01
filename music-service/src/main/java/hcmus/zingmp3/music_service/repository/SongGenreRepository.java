package hcmus.zingmp3.music_service.repository;

import hcmus.zingmp3.music_service.entity.SongGenreEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SongGenreRepository extends ReactiveCrudRepository<SongGenreEntity, String> {
    Flux<SongGenreEntity> findBySongId(String songId);
}
