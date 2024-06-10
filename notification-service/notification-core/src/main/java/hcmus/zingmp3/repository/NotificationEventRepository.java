package hcmus.zingmp3.repository;

import hcmus.zingmp3.notification.domain.events.AbstractNotificationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationEventRepository extends JpaRepository<AbstractNotificationEvent, Long> {
}
