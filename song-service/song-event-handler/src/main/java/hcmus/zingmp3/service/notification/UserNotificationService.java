package hcmus.zingmp3.service.notification;

import java.util.UUID;

public interface UserNotificationService {
    void send(UUID userId, String subject, String body);
}
