package hcmus.zingmp3.web.dto.validator.playlist;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PlaylistAliasNotExistsValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PlaylistAliasNotExists {
    String message() default "is exist";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

}
