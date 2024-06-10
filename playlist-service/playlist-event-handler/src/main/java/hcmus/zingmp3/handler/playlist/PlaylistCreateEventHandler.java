package hcmus.zingmp3.handler.playlist;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Playlist;
import hcmus.zingmp3.common.events.playlist.PlaylistCreateEvent;
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
        var event = gson.fromJson(json, PlaylistCreateEvent.class);
        var playlist = gson.fromJson(gson.toJsonTree(event.getPayload()), Playlist.class);
        playlist.setCreatedBy(event.getCreatedBy());
        playlist.setCreatedDate(event.getTimestamp());
        playlistService.create(playlist);
    }
}
