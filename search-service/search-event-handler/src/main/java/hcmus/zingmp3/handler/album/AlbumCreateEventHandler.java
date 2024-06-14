package hcmus.zingmp3.handler.album;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.domain.model.Album;
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
        Album album = gson.fromJson(
                json,
                Album.class
        );
        albumService.create(album);
    }
}
