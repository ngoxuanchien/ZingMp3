package hcmus.zingmp3.music_service.controller;

import hcmus.zingmp3.music_service.dto.StreamingDTO;
import hcmus.zingmp3.music_service.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/music/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping("/{id}")
    public Mono<StreamingDTO> findById(@PathVariable String id) {
        return streamingService.findById(id);
    }
}
