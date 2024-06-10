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
public class ArtistEmailNotificationEvent extends AbstractNotificationEvent {
    public ArtistEmailNotificationEvent(
            final SystemEmail payload
    ) {
        super(NotificationEventType.ARTIST_EMAIL_NOTIFICATION, payload);
    }
}
