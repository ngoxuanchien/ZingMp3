package zingmp3.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zingmp3.dto.GenreDto;
import zingmp3.service.GenreService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDto>> findAll() {
        return ResponseEntity.ok(genreService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(genreService.findById(id));
    }

    @PostMapping
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<GenreDto> create(@RequestBody GenreDto genreDto) {
        return ResponseEntity.ok(genreService.create(genreDto));
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<GenreDto> update(@PathVariable UUID id, @RequestBody GenreDto genreDto) {
        return ResponseEntity.ok(genreService.update(id, genreDto));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
