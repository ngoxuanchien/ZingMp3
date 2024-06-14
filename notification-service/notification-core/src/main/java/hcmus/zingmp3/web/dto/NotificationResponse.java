package hcmus.zingmp3.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link hcmus.zingmp3.notification.domain.model.Notification}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record NotificationResponse(
        UUID id,
        UUID userId,
        String subject,
        String body,
        LocalDateTime createdDate,
        Boolean isRead
) implements Serializable {
}