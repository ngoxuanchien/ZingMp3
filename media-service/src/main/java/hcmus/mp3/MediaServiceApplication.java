package hcmus.mp3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@Slf4j
public class MediaServiceApplication {

    private static final String AUDIO_PATH = "./data/audio/";

    public static void main(String[] args) {
        SpringApplication.run(MediaServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            Path directoryPath = Paths.get(AUDIO_PATH);
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
        };
    }

}
