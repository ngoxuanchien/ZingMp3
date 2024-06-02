package hcmus.zingmp3.handler;

import hcmus.zingmp3.exception.SongNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SongNotFoundException.class)
    public ResponseEntity<RestErrorResponse> handleSongNotFoundException(SongNotFoundException ex) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(new RestErrorResponse(
                        ex.getMessage(),
                        NOT_FOUND,
                        ZonedDateTime.now()
                ));
    }
}
