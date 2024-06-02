package hcmus.zingmp3.genreevent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GenreEventRepository extends JpaRepository<GenreEvent, UUID> {
}
