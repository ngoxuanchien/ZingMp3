package hcmus.zingmp3.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreRestResponse>> getAllGenres(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(genreService.getAllGenres(pageable));
    }

    @GetMapping("/{genre-id}")
    public ResponseEntity<GenreRestResponse> getGenreById(@PathVariable("genre-id") UUID genreId) {
        return ResponseEntity.ok(genreService.getGenreById(genreId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<GenreRestResponse>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }
}
