package hcmus.zingmp3.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackages = "hcmus.zingmp3")
@ComponentScan(basePackages = "hcmus.zingmp3")
@EnableJpaRepositories(basePackages = "hcmus.zingmp3")
public class SongCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongCoreApplication.class, args);
    }

}