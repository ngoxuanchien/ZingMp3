package hcmus.zingmp3.award.repository;

import hcmus.zingmp3.award.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AwardRepository extends JpaRepository<Award, UUID> {
    Optional<Award> findByName(String name);
    boolean existsByName(String name);
    List<Award> findAllByArtistIdsContaining(UUID artistId);
}
