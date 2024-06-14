package hcmus.zingmp3.web.dto.validator.genre;

import hcmus.zingmp3.service.genre.GenreService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class GenreExistsValidator implements ConstraintValidator<GenreExists, List<UUID>> {
    private final GenreService genreService;

    @Override
    public void initialize(GenreExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<UUID> geneIds, ConstraintValidatorContext context) {
        if (geneIds == null) {
            return true;
        }

        for (UUID genreId : geneIds) {
            if (!genreService.existsById(genreId)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                            String.format("Genre with id %s does not exist", genreId)
                        )
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
