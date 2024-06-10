package hcmus.zingmp3.notification.domain.events;

import hcmus.zingmp3.notification.domain.model.SystemEmail;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AlbumEmailNotificationEvent extends AbstractNotificationEvent {

    public AlbumEmailNotificationEvent(
            final SystemEmail payload
    ) {
        super(NotificationEventType.ALBUM_EMAIL_NOTIFICATION, payload);
    }
}
