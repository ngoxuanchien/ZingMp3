package zingmp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zingmp3.model.Artist;

import java.util.UUID;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {
    boolean existsArtistByName(String name);

    Artist findArtistByName(String name);
}
