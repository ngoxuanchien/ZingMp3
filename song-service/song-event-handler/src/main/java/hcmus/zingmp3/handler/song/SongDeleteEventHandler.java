package hcmus.zingmp3.handler.song;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.events.song.SongDeleteEvent;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.service.notification.EmailNotificationService;
import hcmus.zingmp3.service.notification.UserNotificationService;
import hcmus.zingmp3.service.song.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("SONG_DELETE")
@RequiredArgsConstructor
public class SongDeleteEventHandler implements EventHandler {
    private final Gson gson;
    private final SongService songService;

    private final EmailNotificationService emailNotificationService;
    private final UserNotificationService userNotificationService;

    @Override
    public void handle(JsonObject json) {
        SongDeleteEvent event = gson.fromJson(
                json,
                SongDeleteEvent.class
        );

        Song song = gson.fromJson(gson.toJsonTree(event.getPayload()), Song.class);
        song.setLastModifiedBy(event.getCreatedBy());
        song.setLastModifiedDate(event.getTimestamp());
        songService.delete(song);

        emailNotificationService.sendEmail(song.getCreatedBy(), event.getType().name(), song.getAlias());
        userNotificationService.send(song.getCreatedBy(), event.getType().name(), song.getTitle());
    }
}
