package hcmus.mp3.image;

import hcmus.mp3.image.repository.ImageRepository;
import hcmus.mp3.image.service.DefaultImageService;
import hcmus.mp3.image.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class ImageServiceApplication {

    public static final String IMAGE_PATH = "./data/images/";

    public static void main(String[] args) {
        SpringApplication.run(ImageServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(DefaultImageService imageService) {
        return args -> {
            Path directoryPath = Paths.get(IMAGE_PATH);
            try {
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                    log.info("Directory is created: {}", directoryPath);
                } else {
                    log.info("Directory already exists: {}", directoryPath);
                }
            } catch (Exception e) {
                log.error("Cannot create directory: {}", directoryPath, e);
                throw new RuntimeException(e);
            }
            imageService.saveDefaultImage(Paths.get(IMAGE_PATH + "default-image.jpg"));
        };
    }

}
