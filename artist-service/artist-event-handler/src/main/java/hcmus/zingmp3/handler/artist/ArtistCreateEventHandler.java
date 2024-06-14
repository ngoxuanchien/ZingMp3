package hcmus.zingmp3.handler.artist;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.common.events.ArtistCreateEvent;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.notification.domain.events.ArtistEmailNotificationEvent;
import hcmus.zingmp3.notification.domain.model.SystemEmail;
import hcmus.zingmp3.service.artist.ArtistService;
import hcmus.zingmp3.service.notification.EmailNotificationService;
import hcmus.zingmp3.service.notification.UserNotificationService;
import hcmus.zingmp3.service.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("ARTIST_CREATE")
@RequiredArgsConstructor
public class ArtistCreateEventHandler implements EventHandler {

    private final ArtistService artistService;
    private final Gson gson;
    private final EmailNotificationService emailNotificationService;
    private final UserNotificationService userNotificationService;

    @Override
    public void handle(JsonObject object) {
        ArtistCreateEvent event = gson.fromJson(
                object,
                ArtistCreateEvent.class
        );

        Artist artist = gson.fromJson(gson.toJsonTree(event.getPayload()), Artist.class);
        artist.setCreatedBy(event.getCreatedBy());
        artist.setCreateDate(event.getTimestamp());
        artistService.create(artist);

        emailNotificationService.sendEmail(artist.getCreatedBy(), event.getType().name(), artist.getAlias());
        userNotificationService.send(artist.getCreatedBy(), event.getType().name(), artist.getName());

    }
}
