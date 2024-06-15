package hcmus.mp3.repository;

import hcmus.mp3.domain.model.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AudioRepository extends JpaRepository<Audio, UUID> {
    Optional<Audio> findByPath(String filePath);
}
