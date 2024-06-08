package hcmus.zingmp3.core.web.dto.validator;

import hcmus.zingmp3.core.service.media.MediaService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class MediaIdsExistsValidator implements ConstraintValidator<MediaIdsExists, Set<UUID>> {

    private final MediaService mediaService;

    @Override
    public void initialize(MediaIdsExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Set<UUID> mediaIds, ConstraintValidatorContext context) {
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
