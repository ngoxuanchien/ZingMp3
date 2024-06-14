package hcmus.zingmp3.handler.playlist;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.domain.model.Playlist;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.service.playlist.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("PLAYLIST_CREATE")
@RequiredArgsConstructor
public class PlaylistCreateEventHandler implements EventHandler {
    private final Gson gson;
    private final PlaylistService playlistService;

    @Override
    public void handle(JsonObject json) {
        var playlist = gson.fromJson(json, Playlist.class);
        playlistService.create(playlist);
    }
}
