package hcmus.zingmp3.content_delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ContentDeliveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentDeliveryServiceApplication.class, args);
    }
}
