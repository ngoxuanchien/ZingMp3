package hcmus.zingmp3.handler.song;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.domain.model.Song;
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
        Song song = gson.fromJson(json, Song.class);
        songService.update(song);

    }
}
