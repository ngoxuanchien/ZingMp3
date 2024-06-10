package hcmus.zingmp3.service.notification;

import hcmus.zingmp3.notification.domain.events.AlbumEmailNotificationEvent;
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
    @Value("${album.url}")
    private String albumUrl;

    private final KafkaProducer producer;

    @Override
    public void sendEmail(UUID userId, String subject, String albumAlias) {
        var email = SystemEmail.builder()
                .to(userId)
                .subject(subject)
                .body(albumUrl + "?alias=" + albumAlias)
                .build();
        var emailEvent = new AlbumEmailNotificationEvent(email);
        producer.send(emailEvent);
    }
}
