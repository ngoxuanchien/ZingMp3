package hcmus.zingmp3.validation;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DateValidator {
    private final String dateFormat = "dd-MM-yyyy";
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);;

    public boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, this.dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}