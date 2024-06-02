package hcmus.zingmp3.content_delivery.service.component;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

public class UUIDValidator {
    private static final Pattern UUID_PATTERN = Pattern.compile("(^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}|00000000-0000-0000-0000-000000000000)$", Pattern.CASE_INSENSITIVE);

    private static final String DEFAULT_UUID = "00000000-0000-0000-0000-000000000000";
    public static boolean isValidUUID(String uuid) {
        if (uuid.equals(DEFAULT_UUID)) {
            return true;
        }
        return UUID_PATTERN.matcher(uuid).matches();
    }
}