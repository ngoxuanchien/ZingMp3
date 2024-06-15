package hcmus.zingmp3.mapper;

import com.google.gson.JsonObject;
import hcmus.zingmp3.dto.song.SongRequest;

import java.util.List;
import java.util.UUID;

import static hcmus.zingmp3.Main.*;

public class SongMapper {

    public SongRequest toRequest(JsonObject jsonObject) {
        String alias = jsonObject.get("alias").getAsString();

        UUID thumbnailId = null;
        List<UUID> artistIds = null;
        List<UUID> genreIds = null;
        List<UUID> composerIds = null;

        if (jsonObject.get("thumbnailM") != null) {
            thumbnailId = imageCloneService.cloneImage(alias, jsonObject.get("thumbnailM").getAsString());
        }

        if (jsonObject.get("artists") != null) {
            artistIds = artistCloneService.cloneArtist(jsonObject.get("artists").getAsJsonArray());
        }

        if (jsonObject.get("genres") != null) {
            genreIds = genreCloneService.cloneGenre(jsonObject.get("genres").getAsJsonArray());
        }


        if (jsonObject.get("composers") != null) {
            composerIds = artistCloneService.cloneArtist(jsonObject.get("composers").getAsJsonArray());
        }

        return new SongRequest(
                alias,
                jsonObject.get("title").getAsString(),
                thumbnailId,
                artistIds,
                genreIds,
                composerIds,
                jsonObject.get("releaseDate").getAsInt(),
                null,
                null,
                null,
                List.of(mediaCloneService.cloneMedia(alias, jsonObject.get("streaming").getAsJsonObject().get("128").getAsString()))
        );
    }
}
