package hcmus.zingmp3.web.controller;

import hcmus.zingmp3.service.genre.GenreService;
import hcmus.zingmp3.web.dto.GenreRequest;
import hcmus.zingmp3.web.dto.GenreResponse;
import hcmus.zingmp3.web.dto.OnCreate;
import hcmus.zingmp3.web.dto.OnUpdate;
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
@RequestMapping("/api/genres")
@Validated
public class GenreController {
    private final GenreService genreService;

    @SecurityRequirement(name = "Keycloak")
    @PostMapping
    public ResponseEntity<GenreResponse> createGenre(
            @RequestBody @Validated(OnCreate.class) GenreRequest request
    ) {
        GenreResponse response = genreService.createGenre(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{genre-id}")
    public ResponseEntity<GenreResponse> getGenre(
            @PathVariable("genre-id") UUID genreId
    ) {
        GenreResponse response = genreService.getGenreById(genreId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<GenreResponse> getGenreByAlias(
            @RequestParam("alias") String alias
    ) {
        GenreResponse response = genreService.getGenreByAlias(alias);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GenreResponse>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<GenreResponse>> getGenreByAlias(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(order), sortBy);
        Pageable pageable = PageRequest.of(page, size, sortData);
        return ResponseEntity.ok(genreService.getAllGenres(pageable));
    }

    @SecurityRequirement(name = "Keycloak")
    @PutMapping
    public ResponseEntity<GenreResponse> updateGenre(
            @RequestBody @Validated(OnUpdate.class) GenreRequest request
    ) {
        GenreResponse response = genreService.updateGenre(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @SecurityRequirement(name = "Keycloak")
    @DeleteMapping("/{genre-id}")
    public ResponseEntity<Void> deleteGenre(
            @PathVariable("genre-id") UUID genreId
    ) {
        genreService.deleteGenre(genreId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
