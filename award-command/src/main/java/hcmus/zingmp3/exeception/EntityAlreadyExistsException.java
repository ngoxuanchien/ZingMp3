package hcmus.zingmp3.award.exeception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AwardAlreadyExistsException extends RuntimeException {
    private final String message;
}