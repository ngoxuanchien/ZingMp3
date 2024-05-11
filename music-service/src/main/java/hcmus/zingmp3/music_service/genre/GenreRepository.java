package hcmus.zingmp3.music_service.genre;

import hcmus.zingmp3.music_service.genre.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID> {
    Optional<Genre> findByAlias(String alias);
}
