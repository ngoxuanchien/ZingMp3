package hcmus.zingmp3.web.dto.validator.artist;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ArtistIdsExistsValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ArtistIdsExists {
    String message() default "does not exist";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
