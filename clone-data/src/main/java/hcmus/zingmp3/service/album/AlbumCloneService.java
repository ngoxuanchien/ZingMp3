package hcmus.zingmp3.service.album;

import com.google.gson.JsonObject;

import java.util.UUID;

public interface AlbumCloneService {
    UUID cloneAlbum(JsonObject jsonObject);

    void cloneAlbum(String albumId);
}
