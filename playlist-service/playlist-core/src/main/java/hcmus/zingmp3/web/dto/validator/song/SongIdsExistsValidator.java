package hcmus.zingmp3.web.dto.validator.song;

import hcmus.zingmp3.service.song.SongService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@RequiredArgsConstructor
public class SongIdsExistsValidator implements ConstraintValidator<SongIdsExists, List<UUID>> {

    private final SongService songService;

    @Override
    public boolean isValid(List<UUID> songIds, ConstraintValidatorContext context) {
        if (songIds == null) {
            return true;
        }

        for (UUID songId : songIds) {
            if (!songService.isExist(songId)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                             String.format("Song with id %s does not exist", songId)
                        )
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
