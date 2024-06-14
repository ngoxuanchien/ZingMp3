package hcmus.zingmp3.repository.jpa;

import hcmus.zingmp3.domain.section.AbstractSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SectionRepository extends JpaRepository<AbstractSection, UUID> {
}
