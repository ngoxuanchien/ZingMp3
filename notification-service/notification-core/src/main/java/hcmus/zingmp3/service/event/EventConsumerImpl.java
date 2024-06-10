package hcmus.zingmp3.service.event;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import hcmus.zingmp3.handler.EventHandler;
import jakarta.transaction.Transactional;
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
    @KafkaListener(topics = "notification-service")
    @Transactional
    public void processEvent(
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
