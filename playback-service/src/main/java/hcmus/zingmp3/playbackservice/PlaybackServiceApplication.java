package hcmus.zingmp3.playbackservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Mono;

import java.io.File;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(
        servers = {
                @Server(url = "${server.gateway.url}", description = "Production server")
        },
        info = @Info(title = "Song Service API", version = "1.0", description = "API for Song Service")
)
public class PlaybackServiceApplication {

    public static void main(String[] args) {
        File workingDir = new File(".");
        String absolutePath = workingDir.getAbsolutePath();
        System.out.println(absolutePath);
        SpringApplication.run(PlaybackServiceApplication.class, args);
    }

}
