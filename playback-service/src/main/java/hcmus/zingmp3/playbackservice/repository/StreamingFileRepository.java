package hcmus.zingmp3.playbackservice.repository;

import hcmus.zingmp3.playbackservice.entity.StreamingFileEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingFileRepository extends ReactiveCrudRepository<StreamingFileEntity, String> {

}
