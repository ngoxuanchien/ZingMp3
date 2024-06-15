package hcmus.zingmp3.web.dto.mapper;

import hcmus.zingmp3.notification.domain.model.Notification;
import hcmus.zingmp3.web.dto.NotificationResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationMapper {
    public NotificationResponse toDto(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getUserId(),
                notification.getSubject(),
                notification.getBody(),
                notification.getCreatedDate(),
                notification.isRead()
        );
    }

    public List<NotificationResponse> toDto(List<Notification> notifications) {
        return notifications.stream().map(this::toDto).toList();
    }

    public Page<NotificationResponse> toDto(Page<Notification> pages) {
        return pages.map(this::toDto);
    }
}
