package hcmus.zingmp3.content_delivery.service.component;

import hcmus.zingmp3.content_delivery.model.entity.AudioFileData;
import hcmus.zingmp3.content_delivery.model.entity.ImageFileData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
public class FileDataService {
    private final String THUMBNAIL_PATH = "./data/thumbnail/";
    private final String AUDIO_PATH = "./data/audio/";

    private void createDirectoryIfNotExists(String path) {
        Path directoryPath = Paths.get(path);
        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
                log.info("Directory is created: {}", directoryPath);
            } else {
                log.info("Directory already exists: {}", directoryPath);
            }
        } catch (Exception e) {
            log.error("Cannot create directory: {}", directoryPath, e);
        }
    }

    public void saveAudio(AudioFileData fileData) {
        String filePath = AUDIO_PATH + fileData.getPath();
        createDirectoryIfNotExists(filePath);
        MultipartFile file = fileData.getFile();
        try {
            file.transferTo(new File(filePath + fileData.getName()).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteIfExit(String path) {
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAudio(AudioFileData fileData) {
        String filePath = AUDIO_PATH + fileData.getPath() + fileData.getName();
        deleteIfExit(filePath);
    }

    public void saveThumbnail(ImageFileData fileData) {
        String filePath = THUMBNAIL_PATH + fileData.getPath();
        System.out.println(filePath);
        createDirectoryIfNotExists(filePath);
        MultipartFile file = fileData.getFile();
        try {
            file.transferTo(new File(filePath + fileData.getName()).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void replaceAudio(AudioFileData oldAudioFileData, AudioFileData newAudioFileData) {
        deleteAudio(oldAudioFileData);
        saveAudio(newAudioFileData);
    }
}
