package zingmp3.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zingmp3.exception.SongNotFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SongNotFoundException.class)
    public ResponseEntity<?> handleException(SongNotFoundException exception) {
        log.info(exception.getMessage());
        return ResponseEntity.badRequest()
                .body(exception.getMessage());
    }
}
