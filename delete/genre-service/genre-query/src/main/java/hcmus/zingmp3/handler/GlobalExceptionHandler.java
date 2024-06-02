package hcmus.zingmp3.handler;

import hcmus.zingmp3.exception.GenreNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<RestErrorResponse> handle(RuntimeException exp) {
        return ResponseEntity.status(NOT_FOUND)
                .body(new RestErrorResponse(
                        exp.getMessage(),
                        NOT_FOUND,
                        ZonedDateTime.now())
                );
    }
}
