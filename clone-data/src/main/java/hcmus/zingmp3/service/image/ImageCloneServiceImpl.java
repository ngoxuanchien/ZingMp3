package hcmus.zingmp3.service.image;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static hcmus.zingmp3.Main.imageService;

public class ImageCloneServiceImpl implements ImageCloneService {

    @Override
    public UUID cloneImage(String fileName, String url) {
        try {
            InputStream in = new URL(url).openStream();
            Path tempFile = Files.createTempFile(fileName, ".jpg");
            Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);

            UUID imageId = imageService.uploadImage(tempFile.toFile().getAbsolutePath());

            Files.delete(tempFile);
            return imageId;
        } catch (Exception e) {
            throw new RuntimeException("Failed to clone image", e);
        }
    }
}
