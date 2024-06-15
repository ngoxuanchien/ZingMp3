package hcmus.zingmp3.common.repository;

import hcmus.zingmp3.common.domain.model.Album;
import hcmus.zingmp3.common.domain.model.AlbumStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
    Optional<Album> findByAlias(String alias);
    boolean existsByAlias(String alias);

    Page<Album> findAllByCreatedBy(UUID userId, Pageable pageable);

    Page<Album> findAllByTitleContaining(String title, Pageable pageable);

    Page<Album> findAllByTitleContainingAndStatusInAndCreatedBy(String title, List<AlbumStatus> status, UUID userId, Pageable pageable);

    Page<Album> findAllByStatusInAndCreatedBy(List<AlbumStatus> status, UUID userId, Pageable pageable);

    List<Album> findAllByStatus(AlbumStatus albumStatus);
}
