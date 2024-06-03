package hcmus.zingmp3.handler;

import hcmus.zingmp3.exception.GenreNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.HashMap;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            GenreNotFoundException.class
    })
    public ResponseEntity<?> handleSongNotFoundException(RuntimeException exp) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(new RestExceptionResponse(
                        exp.getMessage(),
                        NOT_FOUND,
                        ZonedDateTime.now()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }
}