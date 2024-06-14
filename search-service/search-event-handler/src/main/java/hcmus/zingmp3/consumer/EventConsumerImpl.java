package hcmus.zingmp3.consumer;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import hcmus.zingmp3.handler.EventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventConsumerImpl implements EventConsumer {

    private final Map<String, EventHandler> factories;

    @Override
    @KafkaListener(topics = {
            "song-service",
            "playlist-service",
            "artist-service"
    })
    public void process(String payload) {
        try {
            log.info("Received message: {}", payload);
            JsonObject json = JsonParser.parseString(payload)
                    .getAsJsonObject();
            String type = json.get("type")
                    .getAsString();
            JsonObject jsonObject = json.get("payload")
                    .getAsJsonObject();
            factories.get(type)
                    .handle(
                            jsonObject
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
