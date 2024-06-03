package hcmus.zingmp3.common.repository;

import hcmus.zingmp3.common.domain.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
    boolean existsByAlias(String alias);
    Optional<Genre> findByAlias(String alias);
}
