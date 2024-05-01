package hcmus.zingmp3.music_service.repository;

import hcmus.zingmp3.music_service.entity.SongUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface SongUserRepository extends ReactiveCrudRepository<SongUserEntity, String> {
    Flux<SongUserEntity> findAllByUserId(String userId, Pageable pageable);
}
