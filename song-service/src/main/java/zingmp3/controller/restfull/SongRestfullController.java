package zingmp3.controller.restfull;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zingmp3.dto.SongDTO;
import zingmp3.service.SongService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/song")
public class SongRestfullController {
    private final SongService songService;

    @GetMapping
    public ResponseEntity<List<SongDTO>> getAllSongs() {
        return ResponseEntity.ok(songService.findAll());
    }

    @GetMapping("/{songId}")
    public ResponseEntity<SongDTO> getSongById(
            @PathVariable Integer songId
    ) {
        return ResponseEntity.ok(songService.findById(songId));
    }

    @PostMapping
    public ResponseEntity<SongDTO> createSong(
            @RequestBody SongDTO request
    ) {
        return ResponseEntity.ok(songService.createSong(request));
    }

    @PutMapping("/{songId}")
    public ResponseEntity<SongDTO> updateSong(
            @PathVariable Integer songId,
            @RequestBody SongDTO request
    ) {
        return ResponseEntity.ok(songService.updateSong(songId, request));
    }

    @DeleteMapping("{songId}")
    public ResponseEntity<?> deleteSong(
            @PathVariable Integer songId
    ) {
        songService.delete(songId);
        return ResponseEntity.noContent().build();
    }
}
