package hcmus.zingmp3.mapper;

import com.google.gson.JsonObject;
import hcmus.zingmp3.dto.playlist.PlaylistRequest;
import hcmus.zingmp3.dto.playlist.PlaylistType;
import hcmus.zingmp3.service.artist.ArtistCloneService;
import hcmus.zingmp3.service.artist.ArtistCloneServiceImpl;
import hcmus.zingmp3.service.image.ImageCloneService;
import hcmus.zingmp3.service.image.ImageCloneServiceImpl;
import hcmus.zingmp3.service.song.SongCloneService;
import hcmus.zingmp3.service.song.SongCloneServiceImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static hcmus.zingmp3.Main.*;

@Component
public class PlaylistMapper {

    public PlaylistRequest toRequest(JsonObject jsonObject) {

        String alias = jsonObject.get("aliasTitle").getAsString();

        UUID thumbnailId = null;
        List<UUID> artistIds = null;
        List<UUID> songIds = null;

        if (jsonObject.get("thumbnailM") != null) {
            thumbnailId = imageCloneService.cloneImage(alias, jsonObject.get("thumbnailM").getAsString());
        }

        if (jsonObject.get("artists") != null) {
            artistIds = artistCloneService.cloneArtist(jsonObject.get("artists").getAsJsonArray());
        }

        if (jsonObject.get("song") != null && jsonObject.get("song").getAsJsonObject().get("items") != null) {
            songIds = songCloneService.cloneSong(jsonObject.get("song").getAsJsonObject().get("items").getAsJsonArray());
        }

        return new PlaylistRequest(
                alias,
                jsonObject.get("title").getAsString(),
                thumbnailId,
                PlaylistType.SYSTEM_PLAYLIST,
                jsonObject.get("sortDescription").getAsString(),
                artistIds,
                songIds,
                true
        );
    }
}
