package hcmus.zingmp3.handler;

import com.google.gson.JsonObject;
import hcmus.zingmp3.common.events.Event;
import org.springframework.kafka.support.Acknowledgment;

public interface EventHandler {
    void handle(
            JsonObject object
    );
}
