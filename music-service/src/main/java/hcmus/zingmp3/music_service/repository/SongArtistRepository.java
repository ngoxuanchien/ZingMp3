package hcmus.zingmp3.music_service.repository;

import hcmus.zingmp3.music_service.dto.ArtistDTO;
import hcmus.zingmp3.music_service.entity.SongArtistEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SongArtistRepository extends ReactiveCrudRepository<SongArtistEntity, String> {
    Flux<SongArtistEntity> findAllBySongId(String songId);
}
