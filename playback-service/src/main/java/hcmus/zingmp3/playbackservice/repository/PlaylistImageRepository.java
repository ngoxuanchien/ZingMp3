package hcmus.zingmp3.playbackservice.repository;

import hcmus.zingmp3.playbackservice.entity.PlaylistImageEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistImageRepository extends ReactiveCrudRepository<PlaylistImageEntity, String> {
}
