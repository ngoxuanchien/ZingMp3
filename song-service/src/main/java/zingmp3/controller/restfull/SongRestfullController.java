package zingmp3.controller.restfull;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zingmp3.dto.SongDTO;
import zingmp3.service.SongService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/songs")
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

    @SecurityRequirement(name = "Keycloak")
    @PostMapping
    public ResponseEntity<SongDTO> createSong(
            @RequestBody SongDTO request
    ) {
        return ResponseEntity.ok(songService.createSong(request));
    }

    @SecurityRequirement(name = "Keycloak")
    @PutMapping("/{songId}")
    public ResponseEntity<SongDTO> updateSong(
            @PathVariable Integer songId,
            @RequestBody SongDTO request
    ) {
        return ResponseEntity.ok(songService.updateSong(songId, request));
    }

    @SecurityRequirement(name = "Keycloak")
    @DeleteMapping("{songId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteSong(
            @PathVariable Integer songId
    ) {
        songService.delete(songId);
        return ResponseEntity.noContent().build();
    }
}
