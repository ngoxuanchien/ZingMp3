package hcmus.mp3.web.controller;


import hcmus.mp3.domain.exception.NotAnAudioFileException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NotAnAudioFileException.class)
    public String handleNotAnAudioFileException(NotAnAudioFileException e) {
        return e.getMessage();
    }
}
