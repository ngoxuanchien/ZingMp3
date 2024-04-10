package zingmp3.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zingmp3.model.Song;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    @NotNull Optional<Song> findById(@NotNull Integer id);
}
