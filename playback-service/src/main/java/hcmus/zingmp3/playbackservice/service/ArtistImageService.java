package hcmus.zingmp3.playbackservice.service;

import hcmus.zingmp3.playbackservice.dto.ArtistImageDTO;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface ArtistImageService {

    byte[] getImageById(String id) throws IOException;

    Mono<ArtistImageDTO> uploadImage(ArtistImageDTO artistImageDTO);
}
