package hcmus.zingmp3.web.dto.validator.song;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SongIdsExistsValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SongIdsExists {
    String message() default "does not exist";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
