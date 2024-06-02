package hcmus.zingmp3.song;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService service;

    @GetMapping("/{song-id}")
    public ResponseEntity<SongRestResponse> getSongById(@PathVariable("song-id") UUID songId) {
        return ResponseEntity.ok(service.getSongById(songId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SongRestResponse>> getAllSongs() {
        return ResponseEntity.ok(service.getAllSongs());
    }

    @GetMapping
    public ResponseEntity<List<SongRestResponse>> getAllSongs(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getAllSongs(pageable));
    }

}
