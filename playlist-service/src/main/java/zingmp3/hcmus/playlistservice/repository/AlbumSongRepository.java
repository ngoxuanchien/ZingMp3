package zingmp3.hcmus.playlistservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import zingmp3.hcmus.playlistservice.entity.AlbumSongEntity;

@Repository
public interface AlbumSongRepository extends ReactiveCrudRepository<AlbumSongEntity, String> {
}
