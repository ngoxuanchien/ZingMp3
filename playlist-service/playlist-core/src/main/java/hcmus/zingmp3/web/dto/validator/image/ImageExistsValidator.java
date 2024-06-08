package hcmus.zingmp3.web.dto.validator.image;

import hcmus.zingmp3.service.image.ImageService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ImageExistsValidator implements ConstraintValidator<ImageExists, UUID> {

    private final ImageService imageService;

    @Override
    public void initialize(ImageExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UUID imageId, ConstraintValidatorContext context) {
        if (imageId == null) {
            return true;
        }

        if (!imageService.existsById(imageId)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                        String.format("Image with id %s does not exist", imageId)
                    )
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
