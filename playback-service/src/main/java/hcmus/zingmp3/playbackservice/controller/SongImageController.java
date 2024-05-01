package hcmus.zingmp3.playbackservice.controller;

import hcmus.zingmp3.playbackservice.dto.SongImageDTO;
import hcmus.zingmp3.playbackservice.entity.SongImageEntity;
import hcmus.zingmp3.playbackservice.service.SongImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/playback/image/song")
public class SongImageController {

    private final SongImageService songImageService;

    @GetMapping("/{id}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String id) throws IOException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(songImageService.loadImage(id));
    }

    @PostMapping
    public Mono<SongImageDTO> uploadImage(@RequestBody SongImageDTO request) {
        return songImageService.uploadImage(request);
    }
}
