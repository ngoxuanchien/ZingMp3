package hcmus.zingmp3.handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.notification.domain.events.SongEmailNotificationEvent;
import hcmus.zingmp3.notification.domain.model.SystemEmail;
import hcmus.zingmp3.repository.NotificationEventRepository;
import hcmus.zingmp3.service.email.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("SONG_EMAIL_NOTIFICATION")
@RequiredArgsConstructor
public class SongEmailNotificationEventHandler implements EventHandler {
    private final EmailService emailService;
    private final Gson gson;
    private final NotificationEventRepository repository;

    @Override
    public void handle(JsonObject object) {
        var event = gson.fromJson(object, SongEmailNotificationEvent.class);
        var systemEmail = gson.fromJson(gson.toJsonTree(event.getPayload()), SystemEmail.class);
        emailService.sendEmail(systemEmail);
        repository.save(event);
    }
}
