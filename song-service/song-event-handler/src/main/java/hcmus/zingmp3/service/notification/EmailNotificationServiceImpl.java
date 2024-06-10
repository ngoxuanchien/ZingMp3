package hcmus.zingmp3.service.notification;

import hcmus.zingmp3.notification.domain.events.ArtistEmailNotificationEvent;
import hcmus.zingmp3.notification.domain.events.SongEmailNotificationEvent;
import hcmus.zingmp3.notification.domain.model.SystemEmail;
import hcmus.zingmp3.service.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailNotificationServiceImpl implements EmailNotificationService {
    @Value("${song.url}")
    private String songUrl;

    private final KafkaProducer producer;

    @Override
    public void sendEmail(UUID userId, String subject, String songAlias) {
        var email = SystemEmail.builder()
                .to(userId)
                .subject(subject)
                .body(songUrl + "?alias=" + songAlias)
                .build();
        var emailEvent = new SongEmailNotificationEvent(email);
        producer.send(emailEvent);
    }
}
