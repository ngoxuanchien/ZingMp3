package hcmus.zingmp3.handler.genre;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Genre;
import hcmus.zingmp3.common.events.genre.GenreUpdateEvent;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.service.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("GENRE_UPDATE")
@RequiredArgsConstructor
public class GenreUpdateEventHandler implements EventHandler {
    private final Gson gson;
    private final GenreService genreService;

    @Override
    public void handle(JsonObject json) {
        GenreUpdateEvent event = gson.fromJson(
                json,
                GenreUpdateEvent.class
        );

        Genre genre = gson.fromJson(gson.toJsonTree(event.getPayload()), Genre.class);
        genreService.update(genre);
    }
}
