package hcmus.zingmp3.music_service.audio;

import hcmus.zingmp3.music_service.audio.model.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AudioRepository extends JpaRepository<Audio, UUID> {
}
