package hcmus.mp3.service.media;

import hcmus.mp3.domain.exception.NotAnAudioFileException;
import hcmus.mp3.domain.exception.ResourceNotFoundException;
import hcmus.mp3.domain.model.Audio;
import hcmus.mp3.repository.AudioRepository;
import hcmus.mp3.web.dto.AudioResponseDto;
import hcmus.mp3.web.dto.mapper.AudioMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AudioServiceImpl implements AudioService {
    private static final String AUDIO_PATH = "./data/audio/";
    private static final String FILE_PATH_FORMAT = "song/%d/";

    private final Tika tika = new Tika();
    private final AudioRepository audioRepository;
    private final AudioMapper audioMapper;

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

    private long getBitrate(MultipartFile file) {
        try {
            Path tempFile = Files.createTempFile("temp", file.getOriginalFilename());
            try (InputStream is = file.getInputStream()) {
                Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }

            AudioFile audioFile = AudioFileIO.read(tempFile.toFile());
            AudioHeader audioHeader = audioFile.getAudioHeader();

            Files.delete(tempFile);

            return audioHeader.getBitRateAsNumber();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private double getDuration(String filePath) {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(filePath));
            return audioFile.getAudioHeader().getTrackLength();
        } catch (Exception e) {
            throw new RuntimeException("Could not read audio file: " + filePath, e);
        }
    }

    @Override
    public AudioResponseDto createAudio(MultipartFile audio, boolean replace) {
        if (!isAudioFile(audio)) {
            throw new NotAnAudioFileException(
                    String.format("File %s is not an audio file", audio.getOriginalFilename())
            );
        }

        String originalFilename = audio.getOriginalFilename();
        long bitrate = getBitrate(audio);
        createDirectoryIfNotExists(AUDIO_PATH + String.format(FILE_PATH_FORMAT, bitrate));
        String path = AUDIO_PATH + String.format(FILE_PATH_FORMAT, bitrate) + originalFilename;
        File newFile = new File(path);
        if (newFile.exists() && !replace) {
            String filenameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));

            originalFilename = filenameWithoutExtension + System.currentTimeMillis() + extension;
            path = AUDIO_PATH + String.format(FILE_PATH_FORMAT, bitrate) + originalFilename;
            newFile = new File(path);
        }

        try {
            audio.transferTo(newFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var entity = Audio.builder()
                .name(audio.getOriginalFilename())
                .type(audio.getContentType())
                .path(path)
                .size(audio.getSize())
                .bitrate(bitrate)
                .duration(getDuration(path))
                .build();

        return audioMapper.toDto(
                audioRepository.findByPath(path)
                        .orElse(audioRepository.save(entity)));
    }

    @Override
    public AudioResponseDto getAudio(UUID audioId) {
        return audioMapper.toDto(audioRepository.findById(audioId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Audio with id %s not found", audioId)
                )));
    }

    @Override
    public List<AudioResponseDto> getAllAudio() {
        return audioMapper.toDtos(audioRepository.findAll());
    }

    @Override
    public void deleteAudio(UUID audioId) {
        audioRepository.deleteById(audioId);
    }
}
