package hcmus.zingmp3.playbackservice.service;

import hcmus.zingmp3.playbackservice.dto.StreamingFileDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface StreamingFileService {
    Mono<StreamingFileDTO> getStreamingFileById(String id);
    Mono<StreamingFileDTO> saveStreamingFile(StreamingFileDTO streamingFileDTO);
}
