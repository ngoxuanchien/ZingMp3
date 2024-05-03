package hcmus.zingmp3.music_service.controller;

import hcmus.zingmp3.music_service.dto.ArtistDTO;
import hcmus.zingmp3.music_service.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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

    @GetMapping("/search")
    public Flux<ArtistDTO> searchArtist(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return artistService.searchArtists(keyword, pageable);
    }
}
