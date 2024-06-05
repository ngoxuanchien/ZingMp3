package hcmus.zingmp3.handler.genre;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Genre;
import hcmus.zingmp3.common.events.genre.GenreCreateEvent;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.service.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("GENRE_CREATE")
@RequiredArgsConstructor
public class GenreCreateEventHandler implements EventHandler {

    private final Gson gson;
    private final GenreService genreService;

    @Override
    public void handle(JsonObject json) {
        GenreCreateEvent event = gson.fromJson(
                json,
                GenreCreateEvent.class
        );

        Genre genre = gson.fromJson(gson.toJsonTree(event.getPayload()), Genre.class);
        genreService.create(genre);
    }
}
