package hcmus.zingmp3.content_delivery.service.component;

import jakarta.validation.constraints.NotNull;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
public class FileTypeDetector {
    private final Tika tika = new Tika();

    public String detectFileType(MultipartFile multipartFile) throws IOException {
        try (InputStream is = multipartFile.getInputStream()) {
            return tika.detect(is);
        }
    }

    public boolean isAudioFile(MultipartFile multipartFile) {
        try {
            String fileType = detectFileType(multipartFile);
            return fileType.startsWith("audio");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isImageFile(@NotNull MultipartFile multipartFile) {
        try {
            String fileType = detectFileType(multipartFile);
            return fileType.startsWith("image");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}