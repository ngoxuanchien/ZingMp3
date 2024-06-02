package hcmus.zingmp3.handler;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


public record RestExceptionResponse(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
}
