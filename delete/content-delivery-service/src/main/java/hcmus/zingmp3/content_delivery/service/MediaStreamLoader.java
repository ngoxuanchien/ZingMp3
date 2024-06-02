package hcmus.zingmp3.content_delivery.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Service
public interface MediaStreamLoader
{
    ResponseEntity<StreamingResponseBody>
    loadEntireMediaFile(String localMediaFilePath) throws IOException;

    ResponseEntity<StreamingResponseBody> loadPartialMediaFile
            (String localMediaFilePath, String rangeValues) throws IOException;

    ResponseEntity<StreamingResponseBody> loadPartialMediaFile
            (String localMediaFilePath, long fileStartPos, long fileEndPos) throws IOException;

    ResponseEntity<StreamingResponseBody> playMediaFile(UUID id, String rangeHeader);
}