package hcmus.zingmp3.content_delivery;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
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
public class ContentDeliveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentDeliveryServiceApplication.class, args);
    }

}
