package hcmus.zingmp3.handler.song;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.events.song.SongDeleteEvent;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.service.song.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("SONG_DELETE")
@RequiredArgsConstructor
public class SongDeleteEventHandler implements EventHandler {
    private final Gson gson;
    private final SongService songService;

    @Override
    public void handle(JsonObject json) {
        SongDeleteEvent event = gson.fromJson(
                json,
                SongDeleteEvent.class
        );

        Song song = gson.fromJson(gson.toJsonTree(event.getPayload()), Song.class);
        songService.delete(song);
    }
}
