package hcmus.zingmp3.playbackservice.controller;

import hcmus.zingmp3.playbackservice.dto.ArtistImageDTO;
import hcmus.zingmp3.playbackservice.service.ArtistImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/api/playback/image/artist")
@RequiredArgsConstructor
public class ArtistImageController {
    private final ArtistImageService artistImageService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getImageById(@PathVariable String id) throws IOException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(artistImageService.getImageById(id));
    }

    @PostMapping
    public Mono<ArtistImageDTO> uploadImage(@RequestBody ArtistImageDTO request) {
        return artistImageService.uploadImage(request);
    }
}
