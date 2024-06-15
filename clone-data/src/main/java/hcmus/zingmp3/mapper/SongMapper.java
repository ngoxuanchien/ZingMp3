package hcmus.zingmp3.mapper;

import com.google.gson.JsonObject;
import hcmus.zingmp3.dto.song.SongRequest;
import hcmus.zingmp3.service.artist.ArtistCloneService;
import hcmus.zingmp3.service.artist.ArtistCloneServiceImpl;
import hcmus.zingmp3.service.genre.GenreCloneService;
import hcmus.zingmp3.service.genre.GenreCloneServiceImpl;
import hcmus.zingmp3.service.image.ImageCloneService;
import hcmus.zingmp3.service.image.ImageCloneServiceImpl;
import hcmus.zingmp3.service.media.MediaCloneService;
import hcmus.zingmp3.service.media.MediaCloneServiceImpl;

import java.util.List;

public class SongMapper {

    private static final ImageCloneService imageCloneService = new ImageCloneServiceImpl();
    private static final GenreCloneService genreCloneService = new GenreCloneServiceImpl();

    private static final ArtistCloneService artistCloneService = new ArtistCloneServiceImpl();
    private static final MediaCloneService mediaCloneService = new MediaCloneServiceImpl();

    public SongRequest toRequest(JsonObject jsonObject) {
        String alias = jsonObject.get("alias").getAsString();

        return new SongRequest(
                alias,
                jsonObject.get("title").getAsString(),
                imageCloneService.cloneImage(alias, jsonObject.get("thumbnailM").getAsString()),
                artistCloneService.cloneArtist(jsonObject.get("artists").getAsJsonArray()),
                genreCloneService.cloneGenre(jsonObject.get("genres").getAsJsonArray()),
                artistCloneService.cloneArtist(jsonObject.get("composers").getAsJsonArray()),
                jsonObject.get("releaseDate").getAsInt(),
                null,
                null,
                null,
                List.of(mediaCloneService.cloneMedia(alias, jsonObject.get("streaming").getAsJsonObject().get("128").getAsString()))
        );
    }
}
