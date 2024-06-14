package hcmus.zingmp3.web.dto.validator.genre;

import hcmus.zingmp3.common.service.genre.GenreQueryService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenreAliasNotExistsValidator implements ConstraintValidator<GenreAliasNotExists, String> {

    private final GenreQueryService queryService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (queryService.existsByAlias(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                        String.format("Genre with alias %s already exists", value)
                    )
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
