package hcmus.zingmp3.playbackservice.controller;

import hcmus.zingmp3.playbackservice.dto.PlaylistImageDTO;
import hcmus.zingmp3.playbackservice.service.PlaylistImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/playback/image/playlist")
public class PlaylistImageController {
    private final PlaylistImageService playlistImageService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getImageById(@PathVariable String id) throws IOException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(playlistImageService.getImageById(id));
    }

    @PostMapping
    public Mono<PlaylistImageDTO> uploadImage(@RequestBody PlaylistImageDTO request) {
        return playlistImageService.uploadImage(request);
    }
}
