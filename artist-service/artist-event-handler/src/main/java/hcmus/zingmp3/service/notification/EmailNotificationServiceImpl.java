package hcmus.zingmp3.service.notification;

import hcmus.zingmp3.notification.domain.events.ArtistEmailNotificationEvent;
import hcmus.zingmp3.notification.domain.model.SystemEmail;
import hcmus.zingmp3.service.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailNotificationServiceImpl implements EmailNotificationService {
    @Value("${artist.url}")
    private String artistUrl;

    private final KafkaProducer producer;

    @Override
    public void sendEmail(UUID userId, String subject, String artistAlias) {
        var email = SystemEmail.builder()
                .to(userId)
                .subject(subject)
                .body(artistUrl + "?alias=" + artistAlias)
                .build();
        var emailEvent = new ArtistEmailNotificationEvent(email);
        producer.send(emailEvent);
    }
}
