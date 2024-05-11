package hcmus.zingmp3.content_delivery;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
//@EnableDiscoveryClient
@OpenAPIDefinition(
        servers = {
                @Server(url = "/", description = "Production server")
        },
        info = @Info(title = "Song Service API", version = "1.0", description = "API for Song Service")
)
@Slf4j
public class ContentDeliveryServiceApplication {


    public static final String SONG_THUMBNAIL_PATH = "./data/song/thumbnail/";
    public static final String ARTIST_THUMBNAIL_PATH = "./data/artist/thumbnail/";
    public static final String PLAYLIST_THUMBNAIL_PATH = "./data/playlist/thumbnail/";
    public static final String SONG_FILE_PATH = "./data/song/";

    public static void main(String[] args) {
        SpringApplication.run(ContentDeliveryServiceApplication.class, args);
    }

}
