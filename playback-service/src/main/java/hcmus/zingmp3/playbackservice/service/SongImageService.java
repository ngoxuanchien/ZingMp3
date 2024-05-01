package hcmus.zingmp3.playbackservice.service;

import hcmus.zingmp3.playbackservice.dto.SongImageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.io.IOException;

@Service
public interface SongImageService {
    Image viewById(String id);

    byte[] loadImage(String imageId) throws IOException;

    Mono<SongImageDTO> uploadImage(hcmus.zingmp3.playbackservice.dto.SongImageDTO request);
}
