package hcmus.zingmp3.config;

import com.google.gson.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Component
public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(
            final JsonElement json,
            final Type typeOfT,
            final JsonDeserializationContext context
    ) throws JsonParseException {
        JsonArray jsonArray = json.getAsJsonArray();
        if (jsonArray.isEmpty()) {
            throw new JsonParseException("JsonArray must not be empty");
        }
        long timestamp = jsonArray.get(0).getAsLong();
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(
                        timestamp / 1000
                ),
                TimeZone.getDefault()
                        .toZoneId()
        );
    }
}
