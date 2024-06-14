package hcmus.zingmp3.handler.artist;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.domain.model.Artist;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.service.artist.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("ARTIST_CREATE")
@RequiredArgsConstructor
public class ArtistCreateEventHandler implements EventHandler {

    private final ArtistService artistService;
    private final Gson gson;

    @Override
    public void handle(JsonObject object) {
        Artist artist = gson.fromJson(object, Artist.class);
        artistService.create(artist);
    }
}
