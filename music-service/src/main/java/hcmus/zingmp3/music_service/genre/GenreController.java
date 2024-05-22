package hcmus.zingmp3.music_service.genre;

import com.google.api.Http;
import hcmus.zingmp3.music_service.genre.model.GenreRequest;
import hcmus.zingmp3.music_service.genre.model.GenreResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/music/genre", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenreController {
    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreResponse> createGenre(
            @RequestBody @Valid GenreRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.createGenre(request));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GenreResponse> getGenreById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }

    @GetMapping
    public ResponseEntity<List<GenreResponse>> getAllGenres(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) Integer size,
            @Schema(description = "Sort by field", allowableValues = {"id", "name", "title", "alias"})
            @RequestParam(defaultValue = "id") String sort,
            @Schema(description = "Sort order", allowableValues = {"asc", "desc"})
            @RequestParam(defaultValue = "asc") String order
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(order), sort);
        Pageable pageable = PageRequest.of(page, size, sortData);

        return ResponseEntity.ok(genreService.getAllGenres(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreResponse> updateGenre(
            @PathVariable UUID id,
            @RequestBody @Valid GenreRequest request
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
