package hcmus.zingmp3.handler.genre;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.domain.model.Genre;
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

        Genre genre = gson.fromJson(json, Genre.class);
        genreService.update(genre);
    }
}
