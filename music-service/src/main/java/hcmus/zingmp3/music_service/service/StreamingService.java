package hcmus.zingmp3.music_service.service;

import hcmus.zingmp3.music_service.dto.StreamingDTO;
import reactor.core.publisher.Mono;

public interface StreamingService {
    Mono<StreamingDTO> findById(String id);
    Mono<StreamingDTO> create(StreamingDTO streamingDTO, String songAlias);
}
