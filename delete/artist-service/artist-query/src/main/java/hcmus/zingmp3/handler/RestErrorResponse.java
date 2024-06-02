package hcmus.zingmp3.handler;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record RestErrorResponse(
        String message,
        HttpStatus status,
        ZonedDateTime timestamp
) {
}
