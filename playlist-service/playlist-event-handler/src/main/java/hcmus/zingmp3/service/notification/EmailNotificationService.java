package hcmus.zingmp3.service.notification;

import java.util.UUID;

public interface EmailNotificationService {
    void sendEmail(UUID userId, String subject, String albumAlias);
}
