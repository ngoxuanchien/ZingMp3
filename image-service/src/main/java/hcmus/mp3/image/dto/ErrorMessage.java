package hcmus.mp3.image.dto;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ErrorMessage(
        String error,
        HttpStatus status,
        ZonedDateTime timestamp
) {
}
