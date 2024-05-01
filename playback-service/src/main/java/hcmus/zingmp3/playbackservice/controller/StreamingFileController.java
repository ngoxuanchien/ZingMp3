package hcmus.zingmp3.playbackservice.controller;

import hcmus.zingmp3.playbackservice.dto.StreamingFileDTO;
import hcmus.zingmp3.playbackservice.service.StreamingFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/playback/streaming")
public class StreamingFileController {

    private final StreamingFileService streamingFileService;

    @PostMapping
    public Mono<StreamingFileDTO> saveStreamFile(@RequestBody StreamingFileDTO request) {
        return streamingFileService.saveStreamingFile(request);
    }

    @GetMapping("/{id}")
    public Mono<StreamingFileDTO> getStreamFileById(@PathVariable String id) {
        return streamingFileService.getStreamingFileById(id);
    }
}
