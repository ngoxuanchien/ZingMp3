package hcmus.zingmp3.service.media;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static hcmus.zingmp3.Main.mediaService;

@Service
public class MediaCloneServiceImpl implements MediaCloneService {

    @Override
    public UUID cloneMedia(String fileName, String url) {
        try {
            InputStream in = new URL(url).openStream();
            Path tempFile = Files.createTempFile(fileName, ".mp3");
            Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);

            UUID mediaId = mediaService.uploadMedia(tempFile.toFile().getAbsolutePath());

            Files.delete(tempFile);
            return mediaId;
        } catch (Exception e) {
            throw new RuntimeException("Failed to clone media", e);
        }
    }
}
