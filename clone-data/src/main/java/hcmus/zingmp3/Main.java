package hcmus.zingmp3;

import com.google.gson.*;
import hcmus.zingmp3.dto.User;
import hcmus.zingmp3.dto.album.AlbumRequest;
import hcmus.zingmp3.dto.album.AlbumResponse;
import hcmus.zingmp3.dto.album.AlbumType;
import hcmus.zingmp3.dto.artist.ArtistRequest;
import hcmus.zingmp3.dto.artist.ArtistResponse;
import hcmus.zingmp3.dto.genre.GenreRequest;
import hcmus.zingmp3.dto.genre.GenreResponse;
import hcmus.zingmp3.dto.song.SongRequest;
import hcmus.zingmp3.dto.song.SongResponse;
import hcmus.zingmp3.service.album.AlbumService;
import hcmus.zingmp3.service.album.AlbumServiceImpl;
import hcmus.zingmp3.service.artist.ArtistService;
import hcmus.zingmp3.service.artist.ArtistServiceImpl;
import hcmus.zingmp3.service.genre.GenreService;
import hcmus.zingmp3.service.genre.GenreServiceImpl;
import hcmus.zingmp3.service.image.ImageService;
import hcmus.zingmp3.service.image.ImageServiceImpl;
import hcmus.zingmp3.service.media.MediaService;
import hcmus.zingmp3.service.media.MediaServiceImpl;
import hcmus.zingmp3.service.song.SongService;
import hcmus.zingmp3.service.song.SongServiceImpl;
import hcmus.zingmp3.service.user.UserService;
import hcmus.zingmp3.service.user.UserServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class Main {
    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(ZonedDateTime.class, (JsonDeserializer<ZonedDateTime>) (json, type, jsonDeserializationContext) -> ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()))
            .registerTypeAdapter(ZonedDateTime.class, (JsonSerializer<ZonedDateTime>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)))
            .create();
    private static final UserService userService = new UserServiceImpl();
    private static final MediaService mediaService = new MediaServiceImpl(gson);
    public static User user;
    private static final ImageService imageService = new ImageServiceImpl();
    private static final ArtistService artistService = new ArtistServiceImpl();
    private static final SongService songService = new SongServiceImpl();
    private static final GenreService genreService = new GenreServiceImpl();
    private static final AlbumService albumService = new AlbumServiceImpl();

    public static void main(String[] args) {
        user = userService.getAccessToken("nxc.hcmus@gmail.com", "123456789");
        System.out.println(user);
        UUID mediaId = mediaService.uploadMedia("src/main/resources/test.mp3");

        UUID thumbnailId = imageService.uploadImage("src/main/resources/testImage.jpg");

        GenreRequest genreRequest = new GenreRequest(
                "alias-test",
                "name-test"
        );

        GenreResponse genre = genreService.createGenre(genreRequest);


        ArtistRequest artistRequest = new ArtistRequest(
                "alias-test",
                thumbnailId,
                "name-test",
                "realName-test"
        );

        ArtistResponse artist = artistService.createArtist(artistRequest);

        var songRequest = new SongRequest(
                   "alias-test",
                   "title-test",
                   thumbnailId,
                   List.of(artist.id()),
                   List.of(genre.id()),
                   List.of(artist.id()),
                   0,
                   null,
                   null,
                   "test-lyric",
                   List.of(mediaId)
               );

        SongResponse song = songService.createSong(songRequest);

        AlbumRequest albumRequest = new AlbumRequest(
                "alias-test",
                thumbnailId,
                "title-test",
                AlbumType.ALBUM,
                "description-test",
                List.of(artist.id()),
                LocalDateTime.now(),
                List.of(song.id())
        );

        AlbumResponse album = albumService.createAlbum(albumRequest);



        albumService.deleteAlbum(album.id());
        songService.deleteSong(song.id());
        artistService.deleteArtist(artist.id());
        genreService.deleteGenre(genre.id());
        userService.logout();

    }
}