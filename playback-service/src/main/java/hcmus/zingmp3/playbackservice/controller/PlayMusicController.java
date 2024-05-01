package hcmus.zingmp3.playbackservice.controller;


import hcmus.zingmp3.playbackservice.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/api/playback/streaming")
@RequiredArgsConstructor
public class PlayMusicController {
    private final StreamingService streamingService;

    @GetMapping(value = "/play/{vid_id}")
    @ResponseBody
    public Mono<ResponseEntity<StreamingResponseBody>> playMediaV02(
            @PathVariable("vid_id") String video_id,
            @RequestHeader(value = "Range", required = false) String rangeHeader

    ) {
        return streamingService.playVideo(video_id, rangeHeader);
    }
}
