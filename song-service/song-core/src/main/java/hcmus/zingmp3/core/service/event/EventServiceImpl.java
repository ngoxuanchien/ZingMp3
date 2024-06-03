package hcmus.zingmp3.core.service.event;

import hcmus.zingmp3.common.events.AbstractEvent;
import hcmus.zingmp3.common.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    @Override
    public void create(
            final AbstractEvent event
    ) {
        repository.save(event);
    }
}
