package hcmus.mp3.web.controller;


import hcmus.mp3.domain.exception.NotAnAudioFileException;
import hcmus.mp3.web.dto.ErrorMessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotAnAudioFileException.class)
    public ResponseEntity<ErrorMessageDto> handler(NotAnAudioFileException e) {
        return ResponseEntity.badRequest()
                .body(new ErrorMessageDto(
                        e.getMessage(),
                        BAD_REQUEST,
                        ZonedDateTime.now()
                ));
    }
}
