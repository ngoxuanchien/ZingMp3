package hcmus.mp3.image.service;

import hcmus.mp3.image.model.Image;
import hcmus.mp3.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DefaultImageService {
    private final ImageRepository repository;

    public void saveDefaultImage(Path targetLocation) throws IOException {
        Resource defaultImage = new ClassPathResource("static/default-image.jpg");
        Files.copy(defaultImage.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        Image image = Image
                .builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .name("default-image.jpg")
                .size(defaultImage.contentLength())
                .type("image/jpeg")
                .path(targetLocation.toString())
                .build();

        repository.save(image);
    }
}
