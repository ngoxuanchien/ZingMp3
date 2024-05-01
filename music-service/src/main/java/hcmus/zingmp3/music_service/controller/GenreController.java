package hcmus.zingmp3.music_service.controller;

import hcmus.zingmp3.music_service.dto.GenreDTO;
import hcmus.zingmp3.music_service.service.GenreService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/music/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/{id}")
    public Mono<GenreDTO> findById(@PathVariable String id) {
        return genreService.findById(id);
    }

    @GetMapping("/alias/{alias}")
    public Mono<GenreDTO> findByAlias(@PathVariable String alias) {
        return genreService.findByAlias(alias);
    }

    @PostMapping
    public Mono<GenreDTO> createGenre(@RequestBody GenreDTO request) {
        return genreService.create(request);
    }
}
