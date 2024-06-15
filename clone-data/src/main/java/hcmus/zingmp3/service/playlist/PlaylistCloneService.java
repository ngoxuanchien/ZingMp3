package hcmus.zingmp3.service.playlist;

import com.google.gson.JsonObject;

import java.util.UUID;

public interface PlaylistCloneService {
    UUID clonePlaylist(JsonObject jsonObject);
}
