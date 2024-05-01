package hcmus.zingmp3.music_service.repository;

import hcmus.zingmp3.music_service.entity.StreamingEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingRepository extends ReactiveCrudRepository<StreamingEntity, String> {
}
