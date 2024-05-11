package hcmus.zingmp3.content_delivery.repository;

import hcmus.zingmp3.content_delivery.model.entity.AudioFileData;
import hcmus.zingmp3.content_delivery.model.enums.ObjectType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AudioFileDataRepository extends JpaRepository<AudioFileData, UUID> {
    Page<AudioFileData> findAll(Pageable pageable);
    boolean existsById(UUID audioId);

    Optional<AudioFileData> findByObjectAndNameAndSize(ObjectType object, String name, long size);
}
