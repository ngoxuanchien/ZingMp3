package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.common.domain.model.Genre;
import hcmus.zingmp3.common.events.genre.GenreCreateEvent;
import hcmus.zingmp3.common.events.genre.GenreDeleteEvent;
import hcmus.zingmp3.common.events.genre.GenreUpdateEvent;
import hcmus.zingmp3.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreCommandServiceImpl implements GenreCommandService {

    private final EventService eventService;

    @Override
    public void update(Genre object) {
        var event = new GenreUpdateEvent(object);
        eventService.create(event);
    }

    @Override
    public void delete(Genre object) {
        var event = new GenreDeleteEvent(object);
        eventService.create(event);
    }

    @Override
    public void create(Genre object) {
        var event = new GenreCreateEvent(object);
        eventService.create(event);
    }
}
