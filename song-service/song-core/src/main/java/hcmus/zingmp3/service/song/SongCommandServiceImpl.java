package hcmus.zingmp3.service.song;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.events.song.*;
import hcmus.zingmp3.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongCommandServiceImpl implements SongCommandService {
    private final EventService eventService;

    @Override
    public void update(Song object) {
        var event = new SongUpdateEvent(object);
        eventService.create(event);
    }

    @Override
    public void delete(Song object) {
        var event = new SongDeleteEvent(object);
        eventService.create(event);
    }

    @Override
    public void create(Song object) {
        var event = new SongCreateEvent(object);
        eventService.create(event);
    }

    @Override
    public void approved(Song object) {
        var event = new SongApprovedEvent(object);
        eventService.create(event);
    }

    @Override
    public void rejected(Song object) {
        var event = new SongRejectedEvent(object);
        eventService.create(event);
    }

    @Override
    public void released(Song object) {
        var event = new SongReleasedEvent(object);
        eventService.create(event);
    }
}
