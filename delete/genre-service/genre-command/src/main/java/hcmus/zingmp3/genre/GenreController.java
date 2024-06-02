package hcmus.zingmp3.genre;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/genres")
public class GenreController {
    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreRestResponse> createGenre(
            @RequestBody @Valid GenreRestRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(genreService.createGenre(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreRestResponse> updateGenre(
            @PathVariable UUID id,
            @RequestBody @Valid GenreRestRequest request
    ) {
        return ResponseEntity.ok(genreService.updateGenre(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(
            @PathVariable UUID id
    ) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
