package hcmus.zingmp3.audio;

import hcmus.zingmp3.audio.model.Audio;
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
