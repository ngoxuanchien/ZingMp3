package hcmus.zingmp3.core.web.handler;

import hcmus.zingmp3.common.domain.exception.AliasIsExistsException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AliasIsExistsException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage aliasIsExists(
            final AliasIsExistsException e
    ) {
        return new ErrorMessage(
                e.getMessage(),
                BAD_REQUEST,
                null,
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
