package hcmus.mp3.service.stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.UUID;

public interface AudioStreamService {
    ResponseEntity<StreamingResponseBody> streamAudio(UUID audioId, String httpRangeList);
}
