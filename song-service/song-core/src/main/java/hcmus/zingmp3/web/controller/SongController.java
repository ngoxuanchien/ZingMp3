package hcmus.zingmp3.web.controller;

import hcmus.zingmp3.common.domain.model.SongStatus;
import hcmus.zingmp3.service.song.SongService;
import hcmus.zingmp3.web.dto.OnCreate;
import hcmus.zingmp3.web.dto.OnUpdate;
import hcmus.zingmp3.web.dto.SongRequest;
import hcmus.zingmp3.web.dto.SongResponse;
import hcmus.zingmp3.web.dto.validator.genre.GenreExists;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<SongResponse> createSong(
            @RequestBody @Validated(OnCreate.class) SongRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(songService.createSong(request));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<SongResponse>> searchSong(
            @RequestParam("title") String name,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(songService.searchSong(name, pageable));
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
    public ResponseEntity<Page<SongResponse>> getAllSongsByPage(
            @RequestParam(value = "page", defaultValue = "0") @Min(0) int page,
            @RequestParam(value = "size", defaultValue = "10") @Min(1) int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @Schema(description = "Sort order", allowableValues = {"asc", "desc"})
            @RequestParam(defaultValue = "asc") String order
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(songService.getAllSongs(pageable));
    }

    @GetMapping("/my-songs")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<Page<SongResponse>> getMySongs(
            @RequestParam(value = "status", required = false) List<SongStatus> status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        if (status != null) {
            return ResponseEntity.ok(songService.getAllMySongs(status, pageable));
        } else {
            return ResponseEntity.ok(songService.getAllMySongs(pageable));
        }
    }

    @GetMapping("/my-songs/search")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<Page<SongResponse>> searchMySongs(
            @RequestParam("title") String title,
            @RequestParam(value = "status") List<SongStatus> status,
//            @RequestParam(value = "genreIds", required = false) @Validated @GenreExists List<UUID> genreIds,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(songService.searchMySongs(title, status, pageable));
    }

    @GetMapping
    public ResponseEntity<SongResponse> getSongByAlias(
            @RequestParam("alias") String alias
    ) {
        return ResponseEntity.ok(songService.getSongByAlias(alias));
    }

    @SecurityRequirement(name = "Keycloak")
    @PutMapping
    public ResponseEntity<SongResponse> updateSong(
            @RequestBody @Validated(OnUpdate.class) SongRequest request
    ) {
        return ResponseEntity.ok(songService.updateSong(request));
    }

    @SecurityRequirement(name = "Keycloak")
    @PutMapping("/approved/{song-alias}")
    public ResponseEntity<?> approveSong(
            @PathVariable("song-alias") String alias
    ) {
        songService.approvedSong(alias);
        return ResponseEntity.accepted().build();
    }

    @SecurityRequirement(name = "Keycloak")
    @PutMapping("/rejected/{song-alias}")
    public ResponseEntity<?> rejectSong(
            @PathVariable("song-alias") String alias
    ) {
        songService.rejectedSong(alias);
        return ResponseEntity.accepted().build();
    }

    @SecurityRequirement(name = "Keycloak")
    @PutMapping("/released/{song-alias}")
    public ResponseEntity<?> releaseSong(
            @PathVariable("song-alias") String alias
    ) {
        songService.releasedSong(alias);
        return ResponseEntity.accepted().build();
    }

    @SecurityRequirement(name = "Keycloak")
    @DeleteMapping("/{song-id}")
    public ResponseEntity<?> deleteSong(
            @PathVariable("song-id") UUID songId
    ) {
        songService.deleteSong(songId);
        return ResponseEntity.noContent().build();
    }
}


