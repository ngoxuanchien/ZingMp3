package hcmus.zingmp3.web.controller;

import hcmus.zingmp3.service.playlist.PlaylistService;
import hcmus.zingmp3.web.dto.OnCreate;
import hcmus.zingmp3.web.dto.PlaylistRequest;
import hcmus.zingmp3.web.dto.PlaylistResponse;
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
@Validated
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<PlaylistResponse> createPlaylist(
          @RequestBody @Validated(OnCreate.class) PlaylistRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(playlistService.createPlaylist(request));
    }

    @GetMapping("/{playlist-id}")
    public ResponseEntity<PlaylistResponse> getPlaylistById(
            @PathVariable("playlist-id") UUID playlistId
    ) {
        return ResponseEntity.ok(playlistService.getPlaylistById(playlistId));
    }

    @GetMapping
    public ResponseEntity<PlaylistResponse> getPlaylistByAlias(
            @RequestParam String alias
    ) {
        return ResponseEntity.ok(playlistService.getPlaylistByAlias(alias));
    }

    @GetMapping("/page/")
    public ResponseEntity<List<PlaylistResponse>> getAllPlaylists(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sortBy));
        return ResponseEntity.ok(playlistService.getAllPlaylists(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlaylistResponse>> getAllPlaylists() {
        return ResponseEntity.ok(playlistService.getAllPlaylists());
    }

    @PutMapping
    public ResponseEntity<PlaylistResponse> updatePlaylist(
            @RequestBody @Validated(OnCreate.class) PlaylistRequest request
    ) {
        return ResponseEntity.ok(playlistService.updatePlaylist(request));
    }

    @DeleteMapping("/{playlist-id}")
    public ResponseEntity<Void> deletePlaylist(
            @PathVariable("playlist-id") UUID playlistId
    ) {
        playlistService.deletePlaylist(playlistId);
        return ResponseEntity.noContent().build();
    }
}
