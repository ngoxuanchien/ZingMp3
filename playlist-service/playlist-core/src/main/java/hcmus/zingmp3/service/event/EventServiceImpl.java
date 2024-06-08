package hcmus.zingmp3.service.event;

import hcmus.zingmp3.common.events.AbstractEvent;
import hcmus.zingmp3.common.repository.EventRepository;
import hcmus.zingmp3.service.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final KafkaProducer producer;

    @Override
    public void create(AbstractEvent event) {
        eventRepository.save(event);
        producer.send(event);

    }
}
