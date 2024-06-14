package hcmus.zingmp3.service.notification;

import hcmus.zingmp3.notification.domain.events.UserNotificationEvent;
import hcmus.zingmp3.notification.domain.model.Notification;
import hcmus.zingmp3.service.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserNotificationServiceImpl implements UserNotificationService {

    private final KafkaProducer producer;

    @Override
    public void send(UUID userId, String subject, String body) {
        var notification = Notification.builder()
                .userId(userId)
                .subject(subject)
                .body(body)
                .build();
        var notificationEvent = new UserNotificationEvent(notification);
        producer.send(notificationEvent);
    }
}
