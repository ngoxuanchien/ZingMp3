package hcmus.zingmp3.handler.artist;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.common.events.ArtistDeleteEvent;
import hcmus.zingmp3.common.events.ArtistUpdateEvent;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.service.artist.ArtistService;
import hcmus.zingmp3.service.notification.EmailNotificationService;
import hcmus.zingmp3.service.notification.UserNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("ARTIST_DELETE")
@RequiredArgsConstructor
public class ArtistDeleteEventHandler implements EventHandler {
    private final ArtistService artistService;
    private final Gson gson;
    private final EmailNotificationService emailNotificationService;
    private final UserNotificationService userNotificationService;

    @Override
    public void handle(JsonObject object) {
        ArtistDeleteEvent event = gson.fromJson(
                object,
                ArtistDeleteEvent.class
        );

        Artist artist = gson.fromJson(gson.toJsonTree(event.getPayload()), Artist.class);
        artist.setLastModifiedBy(event.getCreatedBy());
        artist.setLastModifiedDate(event.getTimestamp());
        artistService.delete(artist);

        emailNotificationService.sendEmail(artist.getCreatedBy(), event.getType().name(), artist.getAlias());
        userNotificationService.send(artist.getCreatedBy(), event.getType().name(), artist.getName());

    }
}
