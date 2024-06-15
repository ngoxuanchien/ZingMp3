package hcmus.zingmp3.mapper;

import com.google.gson.JsonObject;
import hcmus.zingmp3.dto.album.AlbumRequest;
import hcmus.zingmp3.dto.album.AlbumType;

import java.time.LocalDateTime;

import static hcmus.zingmp3.Main.*;

public class AlbumMapper {
    public AlbumRequest toRequest(JsonObject jsonObject) {
        String alias = jsonObject.get("aliasTitle").getAsString();

        return new AlbumRequest(
                alias,
                imageCloneService.cloneImage(alias, jsonObject.get("thumbnailM").getAsString()),
                jsonObject.get("title").getAsString(),
                AlbumType.valueOf(jsonObject.get("textType").getAsString().toUpperCase()),
                jsonObject.get("sortDescription").getAsString(),
                artistCloneService.cloneArtist(jsonObject.get("artists").getAsJsonArray()),
                LocalDateTime.now(),
                songCloneService.cloneSong(jsonObject.get("song").getAsJsonObject().get("items").getAsJsonArray())
        );
    }
}
