package hcmus.zingmp3.web.dto.validator.album;

import hcmus.zingmp3.common.service.album.AlbumQueryService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AlbumAliasNotExistsValidator implements ConstraintValidator<AlbumAliasNotExists, String> {

    private final AlbumQueryService queryService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (queryService.existsByAlias(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                        String.format("Album with alias %s already exists", value)
                    )
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
