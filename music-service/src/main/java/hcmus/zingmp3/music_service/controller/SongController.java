package hcmus.zingmp3.music_service.controller;

import hcmus.zingmp3.music_service.dto.SongDTO;
import hcmus.zingmp3.music_service.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/music/song")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @GetMapping("/{id}")
    public Mono<SongDTO> findById(@PathVariable String id) {
        return songService.findById(id);
    }

    @GetMapping("/alias/{alias}")
    public Mono<SongDTO> findByAlias(@PathVariable String alias) {
        return songService.findByAlias(alias);
    }

    @PostMapping
    public Mono<SongDTO> createSong(@RequestBody SongDTO request) {
        return songService.create(request);
    }
}
