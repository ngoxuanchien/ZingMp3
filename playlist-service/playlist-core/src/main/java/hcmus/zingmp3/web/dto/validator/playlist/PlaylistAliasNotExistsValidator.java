package hcmus.zingmp3.web.dto.validator.playlist;

import hcmus.zingmp3.common.service.playlist.PlaylistQueryService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlaylistAliasNotExistsValidator implements ConstraintValidator<PlaylistAliasNotExists, String> {

    private final PlaylistQueryService queryService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (queryService.existsByAlias(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                        String.format("Playlist with alias %s already exists", value)
                    )
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
