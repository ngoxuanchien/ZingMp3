package hcmus.zingmp3.web.dto.validator.artist;

import hcmus.zingmp3.service.artist.ArtistService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class ArtistIdsExistsValidator implements ConstraintValidator<ArtistIdsExists, List<UUID>> {

    private final ArtistService artistService;

    @Override
    public boolean isValid(List<UUID> artistIds, ConstraintValidatorContext context) {
        if (artistIds == null) {
            return true;
        }

        for (UUID artistId : artistIds) {
           if (!artistService.isExist(artistId)) {
               context.disableDefaultConstraintViolation();
               context.buildConstraintViolationWithTemplate(
                            String.format("Artist with id %s does not exist", artistId)
                       )
                       .addConstraintViolation();
               return false;
           }
        }
        return true;
    }
}
