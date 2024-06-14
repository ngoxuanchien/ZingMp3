package hcmus.zingmp3.notification.domain.events;

import hcmus.zingmp3.notification.domain.model.ObjectConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "notification_events")
@AllArgsConstructor
public abstract class AbstractNotificationEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationEventType type;

    @JdbcTypeCode(SqlTypes.JSON)
    @Convert(converter = ObjectConverter.class)
    private Object payload;

    @CreationTimestamp
    private LocalDateTime timestamp;

    public AbstractNotificationEvent(
            final NotificationEventType type,
            final Object payload
    ) {
        this.type = type;
        this.payload = payload;
    }
}
