package zingmp3.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import zingmp3.model.Song;

import java.util.Optional;


public interface SongRepository extends JpaRepository<Song, Integer> {
    @NotNull Optional<Song> findById(@NotNull Integer id);
}
