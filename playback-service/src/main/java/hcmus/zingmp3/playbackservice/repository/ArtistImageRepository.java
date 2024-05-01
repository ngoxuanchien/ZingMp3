package hcmus.zingmp3.playbackservice.repository;

import hcmus.zingmp3.playbackservice.entity.ArtistImageEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistImageRepository extends ReactiveCrudRepository<ArtistImageEntity, String> {
}
