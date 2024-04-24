package zingmp3.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zingmp3.dto.SongDto;
import zingmp3.model.Song;
import zingmp3.service.SongService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/songs")
public class SongController {
    private final SongService songService;

    @GetMapping
    public ResponseEntity<List<SongDto>> findAll() {
        return ResponseEntity.ok(songService.findAll());
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public ResponseEntity<List<SongDto>> findAll(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize
    ) {
        return ResponseEntity.ok(songService.findAll(pageNumber, pageSize));
    }

    @GetMapping("/{songId}")
    public ResponseEntity<SongDto> findById(@PathVariable UUID songId) {
        return ResponseEntity.ok(songService.findById(songId));
    }

    @SecurityRequirement(name = "Keycloak")
    @PostMapping
    public ResponseEntity<SongDto> create(@RequestBody SongDto songDto) {
        return ResponseEntity.ok(songService.create(songDto));
    }

    @SecurityRequirement(name = "Keycloak")
    @PutMapping("/{songId}")
    public ResponseEntity<SongDto> update(
            @PathVariable UUID songId,
            @RequestBody SongDto songDto
    ) {
        return ResponseEntity.ok(songService.update(songId, songDto));
    }

    @SecurityRequirement(name = "Keycloak")
    @DeleteMapping("/{songId}")
    public ResponseEntity<Void> delete(@PathVariable UUID songId) {
        songService.delete(songId);
        return ResponseEntity.noContent().build();
    }
}
