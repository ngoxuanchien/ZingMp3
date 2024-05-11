package hcmus.zingmp3.music_service.audio;

import hcmus.zingmp3.music_service.audio.model.Audio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AudioServiceImpl implements AudioService {
    private final AudioRepository audioRepository;

    @Override
    public Audio getById(String id) {
        return audioRepository.findById(UUID.fromString(id)).orElse(null);
    }
}
