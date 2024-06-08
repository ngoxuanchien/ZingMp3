package hcmus.zingmp3.service.album;

import hcmus.zingmp3.common.domain.model.Album;
import hcmus.zingmp3.common.events.album.*;
import hcmus.zingmp3.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumCommandServiceImpl implements AlbumCommandService {

    private final EventService eventService;

    @Override
    public void create(
            final Album object
    ) {
        var event = new AlbumCreateEvent(object);
        eventService.create(event);
    }

    @Override
    public void update(
            final Album object
    ) {
        var event = new AlbumUpdateEvent(object);
        eventService.create(event);
    }

    @Override
    public void delete(
            final Album object
    ) {
        var event = new AlbumDeleteEvent(object);
        eventService.create(event);
    }

    @Override
    public void approve(
            final Album object
    ) {
        var event = new AlbumApprovedEvent(object);
        eventService.create(event);
    }

    @Override
    public void reject(
            final Album object
    ) {
        var event = new AlbumRejectedEvent(object);
        eventService.create(event);
    }

    @Override
    public void release(
            final Album object
    ) {
        var event = new AlbumReleasedEvent(object);
        eventService.create(event);
    }
}
