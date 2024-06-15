package hcmus.zingmp3.mapper;

import com.google.gson.JsonObject;
import hcmus.zingmp3.dto.artist.ArtistRequest;

import static hcmus.zingmp3.Main.imageCloneService;

public class ArtistMapper {

    public ArtistRequest toRequest(JsonObject jsonObject) {
        String alias = jsonObject.get("alias").getAsString();
        return new ArtistRequest(
                alias,
                imageCloneService.cloneImage(alias, jsonObject.get("thumbnailM").getAsString()),
                jsonObject.get("name").getAsString(),
                jsonObject.get("realname").getAsString()
        );
    }
}
