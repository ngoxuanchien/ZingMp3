package hcmus.zingmp3.common.repository;

import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.common.domain.model.ArtistStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {
    Optional<Artist> findByAlias(String alias);
    boolean existsByAlias(String alias);
    Page<Artist> findAllByNameContainingAndStatusIn(String name, List<ArtistStatus> status, Pageable pageable);
    Page<Artist> findAllByStatusIn(List<ArtistStatus> status, Pageable pageable);
}
