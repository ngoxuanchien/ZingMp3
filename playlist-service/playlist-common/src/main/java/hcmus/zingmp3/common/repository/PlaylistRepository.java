package hcmus.zingmp3.common.repository;

import hcmus.zingmp3.common.domain.model.Playlist;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaylistRepository extends JpaRepository<Playlist, UUID> {
    Optional<Playlist> findByAlias(String alias);
    boolean existsByAlias(String alias);
    List<Playlist> findAllByCreatedBy(UUID userId, Pageable pageable);

    List<Playlist> findAllByTitleContaining(String title, Pageable pageable);
}
