package hcmus.zingmp3.repository.jpa;

import hcmus.zingmp3.domain.section.NewReleaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NewReleaseRepository extends JpaRepository<NewReleaseItem, UUID> {
}
