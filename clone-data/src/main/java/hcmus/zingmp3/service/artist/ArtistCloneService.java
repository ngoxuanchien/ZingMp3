package hcmus.zingmp3.service.artist;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.UUID;

public interface ArtistCloneService {
    UUID cloneArtist(JsonObject jsonObject);

    List<UUID> cloneArtist(JsonArray artists);

    UUID cloneArtist(String artistAlias);
}
