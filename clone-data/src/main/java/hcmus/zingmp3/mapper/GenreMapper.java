package hcmus.zingmp3.mapper;

import com.google.gson.JsonObject;
import hcmus.zingmp3.dto.genre.GenreRequest;

public class GenreMapper {
    public GenreRequest toRequest(JsonObject jsonObject) {
        return new GenreRequest(
                jsonObject.get("alias").getAsString(),
                jsonObject.get("name").getAsString()
        );
    }
}
