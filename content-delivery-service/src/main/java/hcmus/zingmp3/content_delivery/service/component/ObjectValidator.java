package hcmus.zingmp3.content_delivery.service.component;

import hcmus.zingmp3.content_delivery.exception.ObjectNotFoundException;
import hcmus.zingmp3.content_delivery.model.enums.ObjectType;
import org.springframework.stereotype.Component;

@Component
public class ObjectValidator {
    public void validateObject(String object) {
        try {
            ObjectType.valueOf(object.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ObjectNotFoundException("Invalid object type");
        }
    }
}
