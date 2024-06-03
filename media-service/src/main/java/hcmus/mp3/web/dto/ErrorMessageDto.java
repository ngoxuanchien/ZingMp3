package hcmus.mp3.web.dto;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ErrorMessageDto(
        String error,
        HttpStatus status,
        ZonedDateTime timestamp
) {
}
