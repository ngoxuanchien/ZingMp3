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

@Component
public class PlaylistMapper {

    private static final ImageCloneService imageService = new ImageCloneServiceImpl();
    private static final ArtistCloneService artistCloneService = new ArtistCloneServiceImpl();
    private static final SongCloneService songCloneService = new SongCloneServiceImpl();

    public PlaylistRequest toRequest(JsonObject jsonObject) {

        String alias = jsonObject.get("aliasTitle").getAsString();

        return new PlaylistRequest(
                alias,
                jsonObject.get("title").getAsString(),
                imageService.cloneImage(alias, jsonObject.get("thumbnailM").getAsString()),
                PlaylistType.SYSTEM_PLAYLIST,
                jsonObject.get("sortDescription").getAsString(),
                artistCloneService.cloneArtist(jsonObject.get("artists").getAsJsonArray()),
                songCloneService.cloneSong(jsonObject.get("song").getAsJsonObject().get("items").getAsJsonArray()),
                true
        );
    }
}
