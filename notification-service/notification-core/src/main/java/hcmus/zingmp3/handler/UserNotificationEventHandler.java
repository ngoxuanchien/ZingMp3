package hcmus.zingmp3.handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.notification.domain.events.UserNotificationEvent;
import hcmus.zingmp3.notification.domain.model.Notification;
import hcmus.zingmp3.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("USER_NOTIFICATION")
@RequiredArgsConstructor
public class UserNotificationEventHandler implements EventHandler {

    private final NotificationService notificationService;
    private final Gson gson;

    @Override
    public void handle(JsonObject object) {
        var event = gson.fromJson(object, UserNotificationEvent.class);
        var notification = gson.fromJson(gson.toJsonTree(event.getPayload()), Notification.class);
        notificationService.sendNotification(notification);
    }
}
