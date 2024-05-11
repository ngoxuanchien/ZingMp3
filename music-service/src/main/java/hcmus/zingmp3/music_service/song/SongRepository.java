package hcmus.zingmp3.music_service.song;

import hcmus.zingmp3.music_service.song.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {
    Page<Song> findAll(Pageable pageable);
    Optional<Song> findByAlias(String alias);
}
