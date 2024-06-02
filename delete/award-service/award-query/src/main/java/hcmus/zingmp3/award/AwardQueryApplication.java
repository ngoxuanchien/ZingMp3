package hcmus.zingmp3.award;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AwardQueryApplication {
    public static void main(String[] args) {
        SpringApplication.run(AwardQueryApplication.class, args);
    }
}
