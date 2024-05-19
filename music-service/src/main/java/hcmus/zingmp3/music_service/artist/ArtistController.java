package hcmus.zingmp3.music_service.artist;

import hcmus.zingmp3.music_service.artist.model.ArtistRequest;
import hcmus.zingmp3.music_service.artist.model.ArtistResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/music/artist", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArtistController {

    private final ArtistService artistService;

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @RequestBody(
            content = @Content(encoding = {
            @Encoding(name = "artist", contentType = MediaType.APPLICATION_JSON_VALUE),
            @Encoding(name = "awards", contentType = MediaType.APPLICATION_JSON_VALUE)
    }))
    public ResponseEntity<ArtistResponse> createArtist(
            @RequestPart("artist") @Valid ArtistRequest request,
            @RequestPart(value = "awards", required = false) Set<String> awards,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail
    ) {
        System.out.println(awards);
        request.addAllAwards(awards);
        request.setThumbnailFile(thumbnail);
        return ResponseEntity.ok(artistService.createArtist(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponse> getArtistById(
            @PathVariable @UUID String id
    ) {
        return ResponseEntity.ok(artistService.getArtistById(id));
    }

    @GetMapping
    public ResponseEntity<List<ArtistResponse>> getAllArtists(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) Integer size,
            @Schema(description = "Sort by field name", allowableValues = { "id", "name", "alias", "totalFollow",  "national", "realName", "birthday", "createdAt", "modifiedAt" })
            @RequestParam(defaultValue = "id") String sortBy,
            @Schema(description = "Sort order", allowableValues = { "asc", "desc" })
            @RequestParam(defaultValue = "asc") String order
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(order), sortBy);
        Pageable pageable = PageRequest.of(page, size, sortData);
        return ResponseEntity.ok(artistService.getAllArtists(pageable));
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @RequestBody(
            content = @Content(encoding = {
                    @Encoding(name = "artist", contentType = MediaType.APPLICATION_JSON_VALUE),
                    @Encoding(name = "awards", contentType = MediaType.APPLICATION_JSON_VALUE)
    }))
    public ResponseEntity<ArtistResponse> updateArtist(
            @UUID
            @PathVariable String id,
            @RequestPart("artist") @Valid ArtistRequest request,
            @RequestPart(value = "awards", required = false) Set<String> awards,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail
    ) {
        request.addAllAwards(awards);
        request.setThumbnailFile(thumbnail);
        return ResponseEntity.ok(artistService.updateArtist(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(
            @UUID
            @PathVariable String id
    ) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}
