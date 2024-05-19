package zingmp3.hcmus.playlistservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.List;

public class Validator implements ConstraintValidator<ValuesAllowed, String> {
    private String message;
    private List<String> allowable;
    private String propName;

    @Override
    public void initialize(ValuesAllowed constraintAnnotation) {
        this.allowable = Arrays.asList(constraintAnnotation.values());
        this.propName = constraintAnnotation.propName();
        this.message = constraintAnnotation.message().concat(allowable.toString());

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean valid = value == null || this.allowable.contains(value);

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(propName)
                    .addConstraintViolation();
        }
        return valid;
    }
}
