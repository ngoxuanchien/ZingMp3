package hcmus.zingmp3.common.repository;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.domain.model.SongStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, UUID> {
    Optional<Song> findByAlias(String alias);
    boolean existsByAlias(String alias);
    Page<Song> findAllByCreatedBy(UUID userId, Pageable pageable);
    Page<Song> findAllByTitleContaining(String title, Pageable pageable);
    Page<Song> findAllByTitleLikeAndStatusInAndCreatedBy(String title, List<SongStatus> status, UUID createdBy, Pageable pageable);

    Page<Song> findAllByStatusInAndCreatedBy(List<SongStatus> status, UUID userId, Pageable pageable);

    List<Song> findAllByStatus(SongStatus songStatus);
}
