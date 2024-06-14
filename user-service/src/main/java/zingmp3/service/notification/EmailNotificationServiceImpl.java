package zingmp3.service.notification;

import hcmus.zingmp3.notification.domain.events.AlbumEmailNotificationEvent;
import hcmus.zingmp3.notification.domain.model.SystemEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zingmp3.service.producer.KafkaProducer;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailNotificationServiceImpl implements EmailNotificationService {

    private final KafkaProducer producer;

    @Override
    public void sendEmail(UUID userId, String subject, String body) {

        var email = SystemEmail.builder()
                .to(userId)
                .subject(subject)
                .body(body)
                .build();

        var emailEvent = new AlbumEmailNotificationEvent(email);
        producer.send(emailEvent);

    }
}
