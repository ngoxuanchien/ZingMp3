package hcmus.zingmp3.web.controller;

import hcmus.zingmp3.service.playlist.PlaylistService;
import hcmus.zingmp3.web.dto.OnCreate;
import hcmus.zingmp3.web.dto.OnUpdate;
import hcmus.zingmp3.web.dto.PlaylistRequest;
import hcmus.zingmp3.web.dto.PlaylistResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Min;
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

    @SecurityRequirement(name = "Keycloak")
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

    @GetMapping("/search")
    public ResponseEntity<List<PlaylistResponse>> searchPlaylist(
            @RequestParam("title") String title,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(playlistService.searchPlaylist(title, pageable));
    }

    @GetMapping
    public ResponseEntity<PlaylistResponse> getPlaylistByAlias(
            @RequestParam String alias
    ) {
        return ResponseEntity.ok(playlistService.getPlaylistByAlias(alias));
    }

    @GetMapping("/page/")
    public ResponseEntity<List<PlaylistResponse>> getAllPlaylists(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(playlistService.getAllPlaylists(pageable));
    }

    @GetMapping("/my-playlist")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<List<PlaylistResponse>> getMyPlaylists(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(playlistService.getMyPlaylists(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlaylistResponse>> getAllPlaylists() {
        return ResponseEntity.ok(playlistService.getAllPlaylists());
    }

    @SecurityRequirement(name = "Keycloak")
    @PutMapping
    public ResponseEntity<PlaylistResponse> updatePlaylist(
            @RequestBody @Validated(OnUpdate.class) PlaylistRequest request
    ) {
        return ResponseEntity.ok(playlistService.updatePlaylist(request));
    }

    @SecurityRequirement(name = "Keycloak")
    @DeleteMapping("/{playlist-id}")
    public ResponseEntity<Void> deletePlaylist(
            @PathVariable("playlist-id") UUID playlistId
    ) {
        playlistService.deletePlaylist(playlistId);
        return ResponseEntity.noContent().build();
    }
}
