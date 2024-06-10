package hcmus.zingmp3.handler.song;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.events.song.SongApprovedEvent;
import hcmus.zingmp3.common.events.song.SongRejectedEvent;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.service.song.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("SONG_REJECTED")
@RequiredArgsConstructor
public class SongRejectedEventHandler implements EventHandler {
    private final SongService songService;
    private final Gson gson;
    @Override
    public void handle(JsonObject json) {
        SongRejectedEvent event = gson.fromJson(
                json,
                SongRejectedEvent.class
        );

        Song song = gson.fromJson(gson.toJsonTree(event.getPayload()), Song.class);
        song.setLastModifiedBy(event.getCreatedBy());
        song.setLastModifiedDate(event.getTimestamp());
        songService.create(song);
    }
}