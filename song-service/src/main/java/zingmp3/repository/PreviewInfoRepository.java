package zingmp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zingmp3.model.PreviewInfo;

import java.util.UUID;

@Repository
public interface PreviewInfoRepository extends JpaRepository<PreviewInfo, UUID> {
}
