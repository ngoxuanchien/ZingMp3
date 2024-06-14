package hcmus.zingmp3.web.controller;

import hcmus.zingmp3.service.song.SongService;
import hcmus.zingmp3.web.model.response.SongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/discovery/songs")
public class SongController {

    private final SongService songService;

    @GetMapping("/{song-id}")
    public ResponseEntity<SongResponse> getSong(
            @PathVariable(name = "song-id") UUID songId
    ) {
        return ResponseEntity.ok(songService.getSongById(songId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SongResponse>> search(
            @RequestParam(name = "title") String title
    ) {
        return ResponseEntity.ok(songService.getAllSongsByTitle(title));
    }
}
