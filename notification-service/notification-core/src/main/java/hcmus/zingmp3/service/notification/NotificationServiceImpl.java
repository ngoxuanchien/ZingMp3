package hcmus.zingmp3.service.notification;

import hcmus.zingmp3.exception.ResourceNotFoundException;
import hcmus.zingmp3.notification.domain.model.Notification;
import hcmus.zingmp3.repository.NotificationRepository;
import hcmus.zingmp3.web.dto.NotificationResponse;
import hcmus.zingmp3.web.dto.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final NotificationMapper mapper;

    @Override
    public void sendNotification(Notification notification) {
        repository.save(notification);
    }

    @Override
    public Page<NotificationResponse> getNotification(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);
        UUID userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
        return mapper.toDto(repository.findAllByUserId(userId, pageable));
    }

    @Override
    public void markAsRead(UUID id) {
        UUID userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
        Notification notification = repository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Notification with id %s not found", id)
                ));

        notification.setRead(true);
        repository.save(notification);
    }
}
