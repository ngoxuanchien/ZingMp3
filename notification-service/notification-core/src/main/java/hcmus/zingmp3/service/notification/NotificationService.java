package hcmus.zingmp3.service.notification;

import hcmus.zingmp3.notification.domain.model.Notification;
import hcmus.zingmp3.web.dto.NotificationResponse;

import java.util.List;

public interface NotificationService {

    void sendNotification(Notification notification);

    List<NotificationResponse> getNotification(int page, int size);;
}
