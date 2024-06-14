package hcmus.zingmp3.notification.domain.events;

import hcmus.zingmp3.notification.domain.model.Notification;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserNotificationEvent extends AbstractNotificationEvent {
    public UserNotificationEvent(
            final Notification payload
    ) {
        super(NotificationEventType.USER_NOTIFICATION, payload);
    }
}
