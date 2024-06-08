package hcmus.zingmp3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackages = "hcmus.zingmp3")
@EnableJpaRepositories(basePackages = "hcmus.zingmp3.*")
public class PlaylistEventHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaylistEventHandlerApplication.class, args);
    }

}
