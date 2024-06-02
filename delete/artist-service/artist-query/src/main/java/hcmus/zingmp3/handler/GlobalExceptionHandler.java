package hcmus.zingmp3.handler;

import hcmus.zingmp3.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestErrorResponse> handle(EntityNotFoundException exp) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(new RestErrorResponse(
                        exp.getMessage(),
                        NOT_FOUND,
                        ZonedDateTime.now())
                );
    }

}
