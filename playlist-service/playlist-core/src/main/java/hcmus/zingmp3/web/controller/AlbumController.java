package hcmus.zingmp3.web.controller;

import hcmus.zingmp3.common.domain.model.AlbumStatus;
import hcmus.zingmp3.service.album.AlbumService;
import hcmus.zingmp3.web.dto.AlbumRequest;
import hcmus.zingmp3.web.dto.AlbumResponse;
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
@RequestMapping("/api/albums")
@Validated
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<AlbumResponse> createAlbum(
            @RequestBody @Validated(OnCreate.class) AlbumRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(albumService.createAlbum(request));
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<AlbumResponse> getAlbumById(
            @PathVariable UUID albumId
    ) {
        return ResponseEntity.ok(albumService.getAlbumById(albumId));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<AlbumResponse>> searchAlbum(
            @RequestParam("title") String title,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(albumService.searchAlbum(title, pageable));
    }

    @GetMapping
    public ResponseEntity<AlbumResponse> getAlbumByAlias(
            @RequestParam String alias
    ) {
        return ResponseEntity.ok(albumService.getAlbumByAlias(alias));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AlbumResponse>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @GetMapping("/page")
    public ResponseEntity<List<AlbumResponse>> getAllAlbums(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(albumService.getAllAlbums(pageable));
    }

    @GetMapping("/my-albums")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<Page<AlbumResponse>> getMyAlbums(
            @RequestParam(value = "status", required = false) List<AlbumStatus> status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        if (status != null) {
            return ResponseEntity.ok(albumService.getMyAlbums(status, pageable));
        }
        return ResponseEntity.ok(albumService.getMyAlbums(pageable));
    }

    @GetMapping("/my-albums/search")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<Page<AlbumResponse>> searchMyAlbums(
            @RequestParam("title") String title,
            @RequestParam("status") List<AlbumStatus> status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(albumService.searchMyAlbums(title, status, pageable));
    }

    @PutMapping
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<AlbumResponse> updateAlbum(
            @RequestBody @Validated(OnUpdate.class) AlbumRequest request
    ) {
        return ResponseEntity.ok(albumService.updateAlbum(request));
    }

    @SecurityRequirement(name = "Keycloak")
    @PutMapping("/approved/{album-alias}")
    public ResponseEntity<Void> approvedAlbum(
            @PathVariable("album-alias") String alias
    ) {
        albumService.approvedAlbum(alias);
        return ResponseEntity.accepted().build();
    }

    @SecurityRequirement(name = "Keycloak")
    @PutMapping("/rejected/{album-alias}")
    public ResponseEntity<Void> rejectedAlbum(
            @PathVariable("album-alias") String alias
    ) {
        albumService.rejectedAlbum(alias);
        return ResponseEntity.accepted().build();
    }

    @SecurityRequirement(name = "Keycloak")
    @PutMapping("/released/{album-alias}")
    public ResponseEntity<Void> releasedAlbum(
            @PathVariable("album-alias") String alias
    ) {
        albumService.releasedAlbum(alias);
        return ResponseEntity.accepted().build();
    }

//    @PutMapping("/remove-song/{album-id}/{song-id}")
//    public ResponseEntity<AlbumResponse> removeSongFromAlbum(
//            @PathVariable("album-id") UUID albumId,
//            @PathVariable("song-id") UUID songId
//    ) {
//        return ResponseEntity.accepted().body(albumService.removeSongFromAlbum(albumId, songId));
//    }

//    @PutMapping("/add-song/{album-id}/{song-id}")
//    public ResponseEntity<AlbumResponse> addSongToAlbum(
//            @PathVariable("album-id") UUID albumId,
//            @PathVariable("song-id") UUID songId
//    ) {
//        return ResponseEntity.accepted().body(albumService.addSongToAlbum(albumId, songId));
//    }

    @SecurityRequirement(name = "Keycloak")
    @DeleteMapping("/{album-id}")
    public ResponseEntity<Void> deleteAlbum(
            @PathVariable("album-id") UUID albumId
    ) {
        albumService.deleteAlbum(albumId);
        return ResponseEntity.noContent().build();
    }


}
