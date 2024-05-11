package hcmus.zingmp3.music_service.audio;

import hcmus.zingmp3.music_service.audio.model.Audio;
import org.springframework.stereotype.Service;

@Service
public interface AudioService {
    Audio getById(String id);
}
