package hcmus.zingmp3.service.genre;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.UUID;

public interface GenreCloneService {
    List<UUID> cloneGenre(JsonArray genres);

    UUID cloneGenre(JsonObject jsonObject);
}
