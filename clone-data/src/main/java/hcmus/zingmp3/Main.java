package hcmus.zingmp3;

import com.google.gson.Gson;
import hcmus.zingmp3.dto.User;
import hcmus.zingmp3.dto.artist.ArtistRequest;
import hcmus.zingmp3.dto.song.SongRequest;
import hcmus.zingmp3.service.artist.ArtistService;
import hcmus.zingmp3.service.artist.ArtistServiceImpl;
import hcmus.zingmp3.service.image.ImageService;
import hcmus.zingmp3.service.image.ImageServiceImpl;
import hcmus.zingmp3.service.media.MediaService;
import hcmus.zingmp3.service.media.MediaServiceImpl;
import hcmus.zingmp3.service.song.SongService;
import hcmus.zingmp3.service.song.SongServiceImpl;
import hcmus.zingmp3.service.user.UserService;
import hcmus.zingmp3.service.user.UserServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.http.HttpClient;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class Main {
    public static final Gson gson = new Gson();
    private static final UserService userService = new UserServiceImpl();
    private static final MediaService mediaService = new MediaServiceImpl(gson);
    public static User user;
    private static final ImageService imageService = new ImageServiceImpl();
    private static final ArtistService artistService = new ArtistServiceImpl();
    private static final SongService songService = new SongServiceImpl();

    public static void main(String[] args) {
        user = userService.getAccessToken("nxc.hcmus@gmail.com", "123456789");
        System.out.println(user);
        UUID mediaId = mediaService.uploadMedia("src/main/resources/test.mp3");

        UUID thumbnailId = imageService.uploadImage("src/main/resources/testImage.jpg");


        ArtistRequest artistRequest = new ArtistRequest(
                "alias-test",
                thumbnailId,
                "name-test",
                "realName-test"
        );

        UUID artistId = artistService.createArtist(artistRequest);

       var songRequest = new SongRequest(
                   "alias-test",
                   "title-test",
                   thumbnailId,
                   List.of(artistId),
                   List.of(),
                   List.of(artistId),
                   0,
                   null,
                   null,
                   "test-lyric",
                   List.of(mediaId)
               );

       songService.createSong(songRequest);

        artistService.deleteArtist(artistId);
        userService.logout();

    }
}