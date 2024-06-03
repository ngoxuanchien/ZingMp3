package hcmus.mp3.image.controller;

import hcmus.mp3.image.dto.ErrorMessage;
import hcmus.mp3.image.exception.NotAnImageException;
import hcmus.mp3.image.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            NotAnImageException.class
    })
    public ResponseEntity<ErrorMessage> handle(RuntimeException e) {
        return ResponseEntity.badRequest()
                .body(new ErrorMessage(
                        e.getMessage(),
                        BAD_REQUEST,
                        ZonedDateTime.now()
                ));
    }

    @ExceptionHandler({
            ResourceNotFoundException.class
    })
    public ResponseEntity<ErrorMessage> handle(ResourceNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND)
                .body(new ErrorMessage(
                        e.getMessage(),
                        NOT_FOUND,
                        ZonedDateTime.now()
                ));
    }
}
