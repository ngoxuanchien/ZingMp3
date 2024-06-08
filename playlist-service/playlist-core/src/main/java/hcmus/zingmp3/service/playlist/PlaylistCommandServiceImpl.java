package hcmus.zingmp3.service.playlist;

import hcmus.zingmp3.common.domain.model.Playlist;
import hcmus.zingmp3.common.events.playlist.PlaylistCreateEvent;
import hcmus.zingmp3.common.events.playlist.PlaylistDeleteEvent;
import hcmus.zingmp3.common.events.playlist.PlaylistUpdateEvent;
import hcmus.zingmp3.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistCommandServiceImpl implements PlaylistCommandService {

    private final EventService eventService;

    @Override
    public void create(Playlist object) {
        var event = new PlaylistCreateEvent(object);
        eventService.create(event);
    }

    @Override
    public void update(Playlist object) {
        var event = new PlaylistUpdateEvent(object);
        eventService.create(event);
    }

    @Override
    public void delete(Playlist object) {
        var event = new PlaylistDeleteEvent(object);
        eventService.create(event);
    }
}
