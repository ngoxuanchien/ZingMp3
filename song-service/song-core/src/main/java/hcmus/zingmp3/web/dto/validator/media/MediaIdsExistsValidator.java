package hcmus.zingmp3.web.dto.validator.media;

import hcmus.zingmp3.service.media.MediaService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class MediaIdsExistsValidator implements ConstraintValidator<MediaIdsExists, List<UUID>> {

    private final MediaService mediaService;

    @Override
    public void initialize(MediaIdsExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<UUID> mediaIds, ConstraintValidatorContext context) {
        if (mediaIds == null || mediaIds.isEmpty()) {
            return true;
        }

        for (UUID mediaId : mediaIds) {
            if (!mediaService.isExists(mediaId)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                            String.format("Media with id %s does not exist", mediaId)
                        )
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
