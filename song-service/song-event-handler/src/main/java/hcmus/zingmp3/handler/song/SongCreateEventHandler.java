package hcmus.zingmp3.handler.song;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.events.song.SongCreateEvent;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.service.notification.EmailNotificationService;
import hcmus.zingmp3.service.notification.UserNotificationService;
import hcmus.zingmp3.service.song.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("SONG_CREATE")
@RequiredArgsConstructor
public class SongCreateEventHandler implements EventHandler {
    private final SongService songService;
    private final Gson gson;

    private final EmailNotificationService emailNotificationService;
    private final UserNotificationService userNotificationService;
    @Override
    public void handle(JsonObject json) {
        SongCreateEvent event = gson.fromJson(
                json,
                SongCreateEvent.class
        );

        Song song = gson.fromJson(gson.toJsonTree(event.getPayload()), Song.class);
        song.setCreatedBy(event.getCreatedBy());
        song.setCreatedDate(event.getTimestamp());
        songService.create(song);

        emailNotificationService.sendEmail(song.getCreatedBy(), event.getType().name(), song.getAlias());
        userNotificationService.send(song.getCreatedBy(), event.getType().name(), song.getTitle());
    }
}
