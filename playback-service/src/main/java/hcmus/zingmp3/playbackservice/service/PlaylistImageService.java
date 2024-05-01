package hcmus.zingmp3.playbackservice.service;

import hcmus.zingmp3.playbackservice.dto.PlaylistImageDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public interface PlaylistImageService {
    byte[] getImageById(String id) throws IOException;
    Mono<PlaylistImageDTO> uploadImage(PlaylistImageDTO playlistImageDTO);
}
