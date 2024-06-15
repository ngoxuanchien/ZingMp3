package hcmus.zingmp3;

import com.google.gson.Gson;
import hcmus.zingmp3.dto.User;
import hcmus.zingmp3.service.image.ImageService;
import hcmus.zingmp3.service.image.ImageServiceImpl;
import hcmus.zingmp3.service.media.MediaService;
import hcmus.zingmp3.service.media.MediaServiceImpl;
import hcmus.zingmp3.service.user.UserService;
import hcmus.zingmp3.service.user.UserServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.http.HttpClient;

@SpringBootApplication
public class Main {
    private static final Gson gson = new Gson();
    private static final UserService userService = new UserServiceImpl();
    private static final MediaService mediaService = new MediaServiceImpl(gson);
    public static User user;
    private static final ImageService imageService = new ImageServiceImpl();

    public static void main(String[] args) {
        user = userService.getAccessToken("nxc.hcmus@gmail.com", "123456789");
        System.out.println(user);
        mediaService.uploadMedia("src/main/resources/test.mp3");
        imageService.uploadImage("src/main/resources/testImage.jpg");
        userService.logout();

    }
}