package hcmus.zingmp3.core.service.song;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.events.song.SongCreateEvent;
import hcmus.zingmp3.common.events.song.SongDeleteEvent;
import hcmus.zingmp3.common.events.song.SongUpdateEvent;
import hcmus.zingmp3.core.service.event.EventService;
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
}
