package hcmus.zingmp3.web.dto.validator.album;

import hcmus.zingmp3.web.dto.validator.image.ImageExistsValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AlbumAliasNotExistsValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AlbumAliasNotExists {
    String message() default "is exist";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
