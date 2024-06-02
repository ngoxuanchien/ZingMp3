package hcmus.zingmp3.songevent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SongEventRepository extends JpaRepository<SongEvent, UUID> {
}
