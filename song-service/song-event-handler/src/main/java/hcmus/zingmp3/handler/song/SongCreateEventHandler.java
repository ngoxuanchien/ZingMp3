package hcmus.zingmp3.handler.song;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.events.song.SongCreateEvent;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.service.song.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("SONG_CREATE")
@RequiredArgsConstructor
public class SongCreateEventHandler implements EventHandler {
    private final SongService songService;
    private final Gson gson;
    @Override
    public void handle(JsonObject json) {
        SongCreateEvent event = gson.fromJson(
                json,
                SongCreateEvent.class
        );

        Song song = gson.fromJson(gson.toJsonTree(event.getPayload()), Song.class);
        songService.create(song);
    }
}
