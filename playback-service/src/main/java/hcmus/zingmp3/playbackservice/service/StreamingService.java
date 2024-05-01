package hcmus.zingmp3.playbackservice.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface StreamingService {
    ResponseEntity<StreamingResponseBody> loadEntireMediaFile(String localMediaFilePath) throws IOException;

    ResponseEntity<StreamingResponseBody> loadPartialMediaFile
            (String localMediaFilePath, String rangeValues) throws IOException;

    ResponseEntity<StreamingResponseBody> loadPartialMediaFile
            (String localMediaFilePath, long fileStartPos, long fileEndPos) throws IOException;

    Mono<ResponseEntity<StreamingResponseBody>> playVideo(String id, String rangeHeader);
}
