package zingmp3.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zingmp3.model.Song;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {
    @NotNull
    Optional<Song> findById(@NotNull UUID id);

    boolean existsByTitle(@NotNull String title);
}
