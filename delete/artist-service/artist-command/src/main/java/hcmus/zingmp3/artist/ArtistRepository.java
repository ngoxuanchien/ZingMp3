package hcmus.zingmp3.artist;

import io.grpc.MethodDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, UUID> {
    Optional<Artist> findByAlias(String name);

    List<Artist> findAllByAwardsContaining(UUID awardId);
    List<Artist> findAllBySongsContaining(UUID songId);

    @Query("SELECT a FROM Artist a WHERE a.id = :id")
    Optional<Artist> findByIdIncludingDeleted(@Param("id") UUID id);

    List<Artist> findAllByComposedSongsContaining(UUID songId);
}
