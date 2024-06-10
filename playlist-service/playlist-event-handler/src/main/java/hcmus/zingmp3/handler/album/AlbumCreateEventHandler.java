package hcmus.zingmp3.handler.album;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Album;
import hcmus.zingmp3.common.events.album.AlbumCreateEvent;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.service.album.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("ALBUM_CREATE")
@RequiredArgsConstructor
public class AlbumCreateEventHandler implements EventHandler {

    private final Gson gson;
    private final AlbumService albumService;


    @Override
    public void handle(JsonObject json) {
        var event = gson.fromJson(
                json,
                AlbumCreateEvent.class
        );

        var album = gson.fromJson(gson.toJsonTree(event.getPayload()), Album.class);
        album.setCreatedBy(event.getCreatedBy());
        album.setCreatedDate(event.getTimestamp());
        albumService.create(album);
    }
}