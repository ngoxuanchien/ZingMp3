package hcmus.zingmp3.music_service.controller;

import hcmus.zingmp3.music_service.dto.ArtistDTO;
import hcmus.zingmp3.music_service.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/music/artist")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping("/{id}")
    public Mono<ArtistDTO> findById(@PathVariable String id) {
        return artistService.findById(id);
    }

    @GetMapping("/alias/{alias}")
    public Mono<ArtistDTO> findByAlias(@PathVariable String alias) {
        return artistService.findByAlias(alias);
    }

    @PostMapping
    public Mono<ArtistDTO> createArtist(@RequestBody ArtistDTO request) {
        return artistService.create(request);
    }
}
