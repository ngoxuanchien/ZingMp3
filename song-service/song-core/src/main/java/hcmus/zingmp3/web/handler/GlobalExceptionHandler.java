package hcmus.zingmp3.web.handler;

import hcmus.zingmp3.common.domain.exception.ResourceAlreadyExistsException;
import hcmus.zingmp3.common.domain.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorMessage resourceNotFound(
            final ResourceNotFoundException e
    ) {
        return new ErrorMessage(
                e.getMessage(),
                NOT_FOUND,
                null,
                ZonedDateTime.now()
        );
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage aliasIsExists(
            final ResourceAlreadyExistsException e
    ) {
        return new ErrorMessage(
                e.getMessage(),
                BAD_REQUEST,
                null,
                ZonedDateTime.now()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage constraintViolation(
            final ConstraintViolationException e
    ) {
        Map<String, String> errors = e.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        violation -> violation.getMessage(),
                        (existingMessage, newMessage) ->
                                existingMessage + " " + newMessage
                ));
        return new ErrorMessage(
                "Validation failed.",
                BAD_REQUEST,
                errors,
                ZonedDateTime.now()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage validation(
            final MethodArgumentNotValidException e
    ) {
        Map<String, String> errors = e.getBindingResult()
                .getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (existingMessage, newMessage) ->
                                existingMessage + " " + newMessage
                ));
        return new ErrorMessage(
                "Validation failed.",
                BAD_REQUEST,
                errors,
                ZonedDateTime.now()
        );
    }
}
