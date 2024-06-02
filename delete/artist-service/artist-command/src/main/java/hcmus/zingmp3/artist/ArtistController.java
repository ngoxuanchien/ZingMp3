package hcmus.zingmp3.artist;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/artist")
public class ArtistController {

    private final ArtistService artistService;

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @SecurityRequirement(name = "Keycloak")
    @RequestBody(
            content = @Content(encoding = {
            @Encoding(name = "artist", contentType = MediaType.APPLICATION_JSON_VALUE),
            @Encoding(name = "awards", contentType = MediaType.APPLICATION_JSON_VALUE)
    }))
    public ResponseEntity<ArtistRestResponse> createArtist(
            @RequestPart("artist") @Valid ArtistRestRequest request,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(artistService.createArtist(request, thumbnail));
    }


    @GetMapping("/all")
    public ResponseEntity<List<ArtistRestResponse>> getAll() {
        return ResponseEntity
                .ok(artistService.getAllArtist());
    }

    @SecurityRequirement(name = "Keycloak")
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @RequestBody(
            content = @Content(encoding = {
                    @Encoding(name = "artist", contentType = MediaType.APPLICATION_JSON_VALUE),
                    @Encoding(name = "awards", contentType = MediaType.APPLICATION_JSON_VALUE)
    }))
    public ResponseEntity<ArtistRestResponse> updateArtist(
            @PathVariable UUID id,
            @RequestPart("artist") @Valid ArtistRestRequest request,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail
    ) {
        return ResponseEntity.ok(artistService.updateAward(id, request, thumbnail));
    }

    @SecurityRequirement(name = "Keycloak")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(
            @PathVariable UUID id
    ) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}
