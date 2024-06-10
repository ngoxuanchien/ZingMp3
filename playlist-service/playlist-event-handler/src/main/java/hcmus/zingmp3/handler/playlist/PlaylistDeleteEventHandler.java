package hcmus.zingmp3.handler.playlist;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Playlist;
import hcmus.zingmp3.common.events.playlist.PlaylistDeleteEvent;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.service.playlist.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("PLAYLIST_DELETE")
@RequiredArgsConstructor
public class PlaylistDeleteEventHandler implements EventHandler {
    private final Gson gson;
    private final PlaylistService playlistService;

    @Override
    public void handle(JsonObject json) {
        var event = gson.fromJson(json, PlaylistDeleteEvent.class);
        var playlist = gson.fromJson(gson.toJsonTree(event.getPayload()), Playlist.class);
        playlist.setLastModifiedBy(event.getCreatedBy());
        playlist.setLastModifiedDate(event.getTimestamp());
        playlistService.delete(playlist);
    }
}
