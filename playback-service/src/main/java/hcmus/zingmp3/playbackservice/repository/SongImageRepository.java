package hcmus.zingmp3.playbackservice.repository;

import hcmus.zingmp3.playbackservice.entity.SongImageEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongImageRepository extends ReactiveCrudRepository<SongImageEntity, String> {
}
