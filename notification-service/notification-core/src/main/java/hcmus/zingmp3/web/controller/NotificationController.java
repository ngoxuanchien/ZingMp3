package hcmus.zingmp3.web.controller;

import hcmus.zingmp3.service.notification.NotificationService;
import hcmus.zingmp3.web.dto.NotificationResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
@Validated
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<Page<NotificationResponse>> getNotification(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size
    ) {
        return ResponseEntity.ok(notificationService.getNotification(page, size));
    }

    @GetMapping("/is-read")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<?> markAsRead(
            @RequestParam @DecimalMin("0") UUID id
    ) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }
}
