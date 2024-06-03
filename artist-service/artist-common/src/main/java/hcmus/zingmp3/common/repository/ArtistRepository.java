package hcmus.zingmp3.common.repository;

import hcmus.zingmp3.common.domain.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {
    Optional<Artist> findByAlias(String alias);
    boolean existsByAlias(String alias);
}
