package hcmus.zingmp3.core.web.dto.validator.artist;

import hcmus.zingmp3.common.service.artist.ArtistQueryService;
import hcmus.zingmp3.core.service.artist.ArtistService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArtistAliasNotExistsValidator implements ConstraintValidator<ArtistAliasNotExists, String> {
    private final ArtistQueryService queryService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (queryService.existsByAlias(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                        String.format("Artist with alias %s already exists", value)
                    )
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
