package hcmus.zingmp3.handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.notification.domain.events.ArtistEmailNotificationEvent;
import hcmus.zingmp3.notification.domain.model.SystemEmail;
import hcmus.zingmp3.repository.NotificationEventRepository;
import hcmus.zingmp3.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("ARTIST_EMAIL_NOTIFICATION")
@RequiredArgsConstructor
public class ArtistEmailNotificationEventHandler implements EventHandler {

    private final EmailService emailService;

    private final Gson gson;

    private final NotificationEventRepository repository;

    @Override
    public void handle(JsonObject object) {
        var event = gson.fromJson(object, ArtistEmailNotificationEvent.class);
        var systemEmail = gson.fromJson(gson.toJsonTree(event.getPayload()), SystemEmail.class);
        emailService.sendEmail(systemEmail);
        repository.save(event);
    }
}
