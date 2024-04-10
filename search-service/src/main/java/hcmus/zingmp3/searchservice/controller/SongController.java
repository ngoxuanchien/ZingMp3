package hcmus.zingmp3.searchservice.controller;

import hcmus.zingmp3.searchservice.dto.SongDTO;
import hcmus.zingmp3.searchservice.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/song")
public class SongController {
    public final SongService songService;

    @GetMapping("{song-id}")
    public ResponseEntity<SongDTO> getSongById(@PathVariable("song-id") Integer songId) {
        return ResponseEntity.ok(songService.getSongById(songId));
    }

    @GetMapping
    private ResponseEntity<List<SongDTO>> getAllSongs() throws InterruptedException {
        return ResponseEntity.ok(songService.getAllSongs());
    }

    @PostMapping
    private ResponseEntity<SongDTO> createSong(@RequestBody SongDTO request) {
        return ResponseEntity.ok(songService.createSong(request));
    }

    @PutMapping("{song-id}")
    private ResponseEntity<SongDTO> updateSong(@PathVariable("song-id") Integer songId, @RequestBody SongDTO request) {
        return ResponseEntity.ok(songService.updateSong(songId, request));
    }

    @DeleteMapping("{song-id}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteSong(@PathVariable("song-id") Integer songId) {
        songService.deleteSong(songId);
    }
}
