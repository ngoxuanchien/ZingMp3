package hcmus.zingmp3.audio;

import hcmus.zingmp3.audio.model.Audio;
import org.springframework.stereotype.Service;

@Service
public interface AudioService {
    Audio getById(String id);
}
