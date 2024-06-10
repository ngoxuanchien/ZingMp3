package hcmus.mp3.web.controller;

import hcmus.mp3.service.stream.AudioStreamService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/play")
public class PlayAudioController {
    private final AudioStreamService audioStreamService;

    @GetMapping("/{audio-id}")
    public ResponseEntity<StreamingResponseBody> streamAudio(
            @PathVariable("audio-id") final UUID audioId,
            @RequestHeader(value = "Range", required = false) String httpRangeList
    ) {
        return audioStreamService.streamAudio(audioId, httpRangeList);
    }

}
