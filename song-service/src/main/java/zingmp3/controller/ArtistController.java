package zingmp3.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zingmp3.dto.ArtistDto;
import zingmp3.service.ArtistService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistDto>> findAll() {
        return ResponseEntity.ok(artistService.findAll());
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<ArtistDto> findById(@PathVariable UUID artistId) {
        return ResponseEntity.ok(artistService.findById(artistId));
    }

    @PostMapping
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<ArtistDto> create(@RequestBody ArtistDto artistDto) {
        return ResponseEntity.ok(artistService.create(artistDto));
    }

    @PutMapping("/{artistId}")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<ArtistDto> update(@PathVariable UUID artistId, @RequestBody ArtistDto artistDto) {
        return ResponseEntity.ok(artistService.update(artistId, artistDto));
    }

    @DeleteMapping("/{artistId}")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<Void> delete(@PathVariable UUID artistId) {
        artistService.delete(artistId);
        return ResponseEntity.noContent().build();
    }
}
