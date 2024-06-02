package hcmus.zingmp3.content_delivery.service.component;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
public class AudioQualityChecker {
    public long getBitrate(MultipartFile file) {
        try {
            // Tạo một tệp tạm thời
            Path tempFile = Files.createTempFile("temp", file.getOriginalFilename());
            try (InputStream is = file.getInputStream()) {
                Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }

            // Đọc tệp âm thanh
            AudioFile audioFile = AudioFileIO.read(tempFile.toFile());
            AudioHeader audioHeader = audioFile.getAudioHeader();

            // Xóa tệp tạm thời
            Files.delete(tempFile);

            // Trả về bitrate
            return audioHeader.getBitRateAsNumber();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}