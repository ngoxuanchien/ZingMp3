package hcmus.zingmp3;

import com.google.gson.*;
import hcmus.zingmp3.dto.Clone;
import hcmus.zingmp3.dto.User;
import hcmus.zingmp3.dto.album.AlbumRequest;
import hcmus.zingmp3.dto.album.AlbumResponse;
import hcmus.zingmp3.dto.album.AlbumType;
import hcmus.zingmp3.dto.artist.ArtistRequest;
import hcmus.zingmp3.dto.artist.ArtistResponse;
import hcmus.zingmp3.dto.genre.GenreRequest;
import hcmus.zingmp3.dto.genre.GenreResponse;
import hcmus.zingmp3.dto.playlist.PlaylistRequest;
import hcmus.zingmp3.dto.playlist.PlaylistResponse;
import hcmus.zingmp3.dto.playlist.PlaylistType;
import hcmus.zingmp3.dto.song.SongRequest;
import hcmus.zingmp3.dto.song.SongResponse;
import hcmus.zingmp3.mapper.*;
import hcmus.zingmp3.service.CloneService;
import hcmus.zingmp3.service.CloneServiceImpl;
import hcmus.zingmp3.service.album.AlbumCloneService;
import hcmus.zingmp3.service.album.AlbumCloneServiceImpl;
import hcmus.zingmp3.service.album.AlbumService;
import hcmus.zingmp3.service.album.AlbumServiceImpl;
import hcmus.zingmp3.service.artist.ArtistCloneService;
import hcmus.zingmp3.service.artist.ArtistCloneServiceImpl;
import hcmus.zingmp3.service.artist.ArtistService;
import hcmus.zingmp3.service.artist.ArtistServiceImpl;
import hcmus.zingmp3.service.genre.GenreService;
import hcmus.zingmp3.service.genre.GenreServiceImpl;
import hcmus.zingmp3.service.image.ImageCloneService;
import hcmus.zingmp3.service.image.ImageCloneServiceImpl;
import hcmus.zingmp3.service.image.ImageService;
import hcmus.zingmp3.service.image.ImageServiceImpl;
import hcmus.zingmp3.service.media.MediaService;
import hcmus.zingmp3.service.media.MediaServiceImpl;
import hcmus.zingmp3.service.playlist.PlaylistService;
import hcmus.zingmp3.service.playlist.PlaylistServiceImpl;
import hcmus.zingmp3.service.song.SongCloneService;
import hcmus.zingmp3.service.song.SongCloneServiceImpl;
import hcmus.zingmp3.service.song.SongService;
import hcmus.zingmp3.service.song.SongServiceImpl;
import hcmus.zingmp3.service.user.UserService;
import hcmus.zingmp3.service.user.UserServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class Main {
    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(ZonedDateTime.class, (JsonDeserializer<ZonedDateTime>) (json, type, jsonDeserializationContext) -> ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()))
            .registerTypeAdapter(ZonedDateTime.class, (JsonSerializer<ZonedDateTime>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)))
            .create();

    public static User user;
    public static final RestTemplate restTemplate = new RestTemplate();
    public static final ArtistMapper artistMapper = new ArtistMapper();
    public static final ImageCloneService imageCloneService = new ImageCloneServiceImpl();
    public static final ArtistCloneService artistCloneService = new ArtistCloneServiceImpl();
    public static final AlbumMapper albumMapper = new AlbumMapper();
    public static final SongCloneService songCloneService = new SongCloneServiceImpl();
    public static final AlbumCloneService albumCloneService = new AlbumCloneServiceImpl();
    public static final Clone clone = new Clone();
    public static final CloneService cloneService = new CloneServiceImpl();
    public static final SongMapper songMapper = new SongMapper();
    public static final PlaylistMapper playlistMapper = new PlaylistMapper();
    public static final GenreMapper genreMapper = new GenreMapper();

    public static final UserService userService = new UserServiceImpl();
    public static final MediaService mediaService = new MediaServiceImpl();
    public static final ImageService imageService = new ImageServiceImpl();
    public static final ArtistService artistService = new ArtistServiceImpl();
    public static final SongService songService = new SongServiceImpl();
    public static final GenreService genreService = new GenreServiceImpl();
    public static final AlbumService albumService = new AlbumServiceImpl();
    public static final PlaylistService playlistService = new PlaylistServiceImpl();


//    private static final CloneService cloneService = new CloneServiceImpl();
    public static void main(String[] args) {
        user = userService.getAccessToken("nxc.hcmus@gmail.com", "123456789");
        clone.addToClone("ZWZB96AI");

        while (!clone.getToClone().isEmpty()) {
            String id = clone.getToClone().poll();
            cloneService.clonePlaylist(id);
        }
        System.out.println(clone.getCloned().size() + " playlists cloned");

        userService.logout();
    }
}