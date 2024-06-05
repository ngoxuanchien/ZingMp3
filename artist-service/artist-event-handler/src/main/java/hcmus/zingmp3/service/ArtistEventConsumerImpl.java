package hcmus.zingmp3.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import hcmus.zingmp3.handler.EventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArtistEventConsumerImpl implements ArtistEventConsumer {
    private final Map<String, EventHandler> factories;

    @Override
    @KafkaListener(topics = "artist-service")
    public void process(
            final String payload
    ) {
        try {
            log.info("Received message: {}", payload);
            JsonObject json = JsonParser.parseString(payload)
                    .getAsJsonObject();
            String type = json.get("type")
                    .getAsString();
            factories.get(type)
                    .handle(
                            json
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
