package hcmus.zingmp3.common.repository;

import hcmus.zingmp3.common.domain.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
    Optional<Album> findByAlias(String alias);
    boolean existsByAlias(String alias);
}
