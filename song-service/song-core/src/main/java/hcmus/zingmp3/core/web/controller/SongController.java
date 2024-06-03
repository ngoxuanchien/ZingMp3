package hcmus.zingmp3.core.web.controller;

import hcmus.zingmp3.core.service.song.SongService;
import hcmus.zingmp3.core.web.dto.OnCreate;
import hcmus.zingmp3.core.web.dto.OnUpdate;
import hcmus.zingmp3.core.web.dto.SongRequest;
import hcmus.zingmp3.core.web.dto.SongResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/songs")
@Validated
public class SongController {
    private final SongService songService;

    @PostMapping
    public ResponseEntity<SongResponse> createSong(
            @RequestBody @Validated(OnCreate.class) SongRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(songService.createSong(request));
    }

    @GetMapping("/{song-id}")
    public ResponseEntity<SongResponse> getSongById(
            @PathVariable("song-id") UUID songId
    ) {
        return ResponseEntity.ok(songService.getSongById(songId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SongResponse>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }

    @GetMapping("/page")
    public ResponseEntity<List<SongResponse>> getAllSongsByPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @Schema(description = "Sort order", allowableValues = {"asc", "desc"})
            @RequestParam(defaultValue = "asc") String order
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(songService.getAllSongs(pageable));
    }

    @GetMapping
    public ResponseEntity<SongResponse> getSongByAlias(
            @RequestParam("alias") String alias
    ) {
        return ResponseEntity.ok(songService.getSongByAlias(alias));
    }

    @PutMapping
    public ResponseEntity<SongResponse> updateSong(
            @RequestBody @Validated(OnUpdate.class) SongRequest request
    ) {
        return ResponseEntity.ok(songService.updateSong(request));
    }

    @DeleteMapping("/{song-id}")
    public ResponseEntity<?> deleteSong(
            @PathVariable("song-id") UUID songId
    ) {
        songService.deleteSong(songId);
        return ResponseEntity.noContent().build();
    }
}


