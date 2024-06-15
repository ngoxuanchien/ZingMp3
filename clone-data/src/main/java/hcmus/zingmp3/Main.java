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
import hcmus.zingmp3.dto.playlist.PlaylistRequest;
import hcmus.zingmp3.dto.playlist.PlaylistResponse;
import hcmus.zingmp3.dto.playlist.PlaylistType;
import hcmus.zingmp3.dto.song.SongRequest;
import hcmus.zingmp3.dto.song.SongResponse;
import hcmus.zingmp3.mapper.AlbumMapper;
import hcmus.zingmp3.mapper.ArtistMapper;
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

    private static final UserService userService = new UserServiceImpl();
    private static final MediaService mediaService = new MediaServiceImpl();
    private static final ImageService imageService = new ImageServiceImpl();
    public static final ArtistService artistService = new ArtistServiceImpl();
    private static final SongService songService = new SongServiceImpl();
    private static final GenreService genreService = new GenreServiceImpl();
    public static final AlbumService albumService = new AlbumServiceImpl();
    private static final PlaylistService playlistService = new PlaylistServiceImpl();

    public static Set<String> toClonePlaylists = new HashSet<>();
    public static Set<String> clonedPlaylists = new HashSet<>();


//    private static final CloneService cloneService = new CloneServiceImpl();
    public static void main(String[] args) {
        user = userService.getAccessToken("nxc.hcmus@gmail.com", "123456789");
//        System.out.println(user);
//        UUID mediaId = mediaService.uploadMedia("src/main/resources/test.mp3");
//
//        UUID thumbnailId = imageService.uploadImage("src/main/resources/testImage.jpg");
//
//        GenreRequest genreRequest = new GenreRequest(
//                "alias-test",
//                "name-test"
//        );
//
//        GenreResponse genre = genreService.getOrCreateIfNotExist(genreRequest);
//
//
//        ArtistRequest artistRequest = new ArtistRequest(
//                "alias-test",
//                thumbnailId,
//                "name-test",
//                "realName-test"
//        );
//
//        ArtistResponse artist = artistService.getOrCreateIfNotExist(artistRequest);
//
//        var songRequest = new SongRequest(
//                   "alias-test",
//                   "title-test",
//                   thumbnailId,
//                   List.of(artist.id()),
//                   List.of(genre.id()),
//                   List.of(artist.id()),
//                   0,
//                   null,
//                   null,
//                   "test-lyric",
//                   List.of(mediaId)
//               );
//
//        SongResponse song = songService.getOrCreateIfNotExist(songRequest);
//
//        AlbumRequest albumRequest = new AlbumRequest(
//                "alias-test-album",
//                thumbnailId,
//                "title-test",
//                AlbumType.ALBUM,
//                "description-test",
//                List.of(artist.id()),
//                LocalDateTime.now(),
//                List.of(song.id())
//        );
//
//        AlbumResponse album = albumService.getOrCreateIfNotExist(albumRequest);
//
//        PlaylistRequest playlistRequest = new PlaylistRequest(
//                "alias-test-playlist",
//                "title-test",
//                thumbnailId,
//                PlaylistType.SYSTEM_PLAYLIST,
//                "description-test",
//                List.of(artist.id()),
//                List.of(song.id()),
//                true
//        );
//
//        PlaylistResponse playlist = playlistService.getOrCreateIfNotExist(playlistRequest);
//
//
////        albumService.deleteAlbum(album.id());
////        songService.deleteSong(song.id());
////        artistService.deleteArtist(artist.id());
////        genreService.deleteGenre(genre.id());
//        userService.logout();

//        cloneService.clonePlaylist("ZWZB96AI");

//        songCloneService.cloneSong("ZZDFW9O6");
//        artistCloneService.cloneArtist("Son-Tung-M-TP");
        albumCloneService.cloneAlbum("6BD0WAFU");
    }
}