package hcmus.zingmp3.web.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorMessage(
        String message,
        HttpStatus status,
        Map<String, String> errors,
        ZonedDateTime timestamp
) {
}
