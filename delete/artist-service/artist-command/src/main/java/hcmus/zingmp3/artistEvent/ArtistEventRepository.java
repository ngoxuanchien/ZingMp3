package hcmus.zingmp3.artistEvent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArtistEventRepository extends JpaRepository<ArtistEvent, UUID> {
    @Query("SELECT ae FROM ArtistEvent ae LEFT JOIN FETCH ae.artist")
    List<ArtistEvent> findAllIncludingDeletedArtists();

}
