package hcmus.zingmp3.song;

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

    boolean existsByAlias(String alias);
}
