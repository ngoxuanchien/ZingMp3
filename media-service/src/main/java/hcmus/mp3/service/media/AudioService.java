package hcmus.mp3.service.media;

import hcmus.mp3.web.dto.AudioResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface AudioService {
    AudioResponseDto createAudio(MultipartFile audio, boolean replace);

    AudioResponseDto getAudio(UUID audioId);

    List<AudioResponseDto> getAllAudio();

    void deleteAudio(UUID audioId);
}
