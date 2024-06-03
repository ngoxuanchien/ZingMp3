package hcmus.zingmp3.handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.common.events.ArtistCreateEvent;
import hcmus.zingmp3.service.artist.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component("ARTIST_CREATE")
@RequiredArgsConstructor
public class ArtistCreateEventHandler implements EventHandler {

    private final ArtistService artistService;
    private final Gson gson;

    @Override
    public void handle(JsonObject object) {
        ArtistCreateEvent event = gson.fromJson(
                object,
                ArtistCreateEvent.class
        );

        Artist artist = gson.fromJson(gson.toJsonTree(event.getPayload()), Artist.class);
        artistService.create(artist);
    }
}
