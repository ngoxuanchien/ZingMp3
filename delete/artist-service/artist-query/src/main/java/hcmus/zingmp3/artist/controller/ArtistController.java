package hcmus.zingmp3.artist.controller;

import hcmus.zingmp3.artist.dto.ArtistRestResponse;
import hcmus.zingmp3.artist.repository.ArtistRepository;
import hcmus.zingmp3.artist.service.ArtistService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/artist/")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistRestResponse>> getAllArtists(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
//        Sort sortData = Sort.by(Sort.Direction.fromString(order), sortBy);
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok().body(artistService.getAllArtists(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArtistRestResponse>> getAllArtists() {
        return ResponseEntity.ok().body(artistService.getAllArtists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistRestResponse> getArtistById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(artistService.getArtistById(id));
    }
}
