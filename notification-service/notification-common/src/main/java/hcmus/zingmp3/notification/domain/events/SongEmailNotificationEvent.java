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
public class SongEmailNotificationEvent extends AbstractNotificationEvent {
    public SongEmailNotificationEvent(
            final SystemEmail payload
    ) {
        super(NotificationEventType.SONG_EMAIL_NOTIFICATION, payload);
    }
}
