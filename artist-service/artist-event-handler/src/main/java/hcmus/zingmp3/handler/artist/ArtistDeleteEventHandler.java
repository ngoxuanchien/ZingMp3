package hcmus.zingmp3.handler.artist;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.common.events.ArtistUpdateEvent;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.service.artist.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("ARTIST_DELETE")
@RequiredArgsConstructor
public class ArtistDeleteEventHandler implements EventHandler {
    private final ArtistService artistService;
    private final Gson gson;

    @Override
    public void handle(JsonObject object) {
        ArtistUpdateEvent event = gson.fromJson(
                object,
                ArtistUpdateEvent.class
        );

        Artist artist = (Artist) event.getPayload();
        artist.setLastModifiedBy(event.getCreatedBy());
        artist.setLastModifiedDate(event.getTimestamp());
        artistService.delete(artist);
    }
}
