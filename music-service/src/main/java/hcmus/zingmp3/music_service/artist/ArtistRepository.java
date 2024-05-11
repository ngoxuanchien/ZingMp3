package hcmus.zingmp3.music_service.artist;

import hcmus.zingmp3.music_service.artist.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, UUID> {
    Optional<Artist> findByAlias(String name);
}
