package zingmp3.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zingmp3.dto.SongDto;
import zingmp3.model.Song;
import zingmp3.service.SongService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/song")
public class SongRestfullController {
    private final SongService songService;

    @GetMapping
    public ResponseEntity<List<Song>> getAllSong() {
        return ResponseEntity.ok(songService.getAllSong());
    }

    @GetMapping("/{songId}")
    public ResponseEntity<Song> getSong(@PathVariable Integer songId) {
        return ResponseEntity.ok(songService.getSong(songId));
    }

    @PostMapping
    public ResponseEntity<Song> addNewSong(@RequestBody SongDto request) {
        return ResponseEntity.ok(songService.addNewSong(request));
    }

    @PutMapping("/{songId}")
    public ResponseEntity<Song> updateSong(@PathVariable Integer songId, @RequestBody SongDto request) {
        return ResponseEntity.ok(songService.updateSong(songId, request));
    }

    @DeleteMapping("{songId}")
    public ResponseEntity<?> deleteSong(@PathVariable Integer songId) {
        songService.deleteSongInService(songId);
        return ResponseEntity.accepted().build();
    }
}
