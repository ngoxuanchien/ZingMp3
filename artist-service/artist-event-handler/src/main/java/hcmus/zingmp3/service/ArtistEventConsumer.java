package hcmus.zingmp3.service;

import hcmus.zingmp3.common.events.Event;
import org.springframework.kafka.support.Acknowledgment;

public interface ArtistEventConsumer {
    void process(String payload);
}
