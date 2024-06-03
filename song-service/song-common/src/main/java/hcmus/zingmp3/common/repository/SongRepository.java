package hcmus.zingmp3.common.repository;

import hcmus.zingmp3.common.domain.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, UUID> {
    Optional<Song> findByAlias(String alias);
    boolean existsByAlias(String alias);
}
