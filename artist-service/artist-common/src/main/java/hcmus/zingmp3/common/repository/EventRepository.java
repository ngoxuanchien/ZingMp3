package hcmus.zingmp3.common.repository;

import hcmus.zingmp3.common.events.AbstractEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<AbstractEvent, Long> {
}
