package hcmus.zingmp3.web.controller;

import hcmus.zingmp3.service.album.AlbumService;
import hcmus.zingmp3.web.dto.AlbumRequest;
import hcmus.zingmp3.web.dto.AlbumResponse;
import hcmus.zingmp3.web.dto.OnCreate;
import hcmus.zingmp3.web.dto.OnUpdate;
import lombok.RequiredArgsConstructor;
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
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sortBy));
        return ResponseEntity.ok(albumService.getAllAlbums(pageable));
    }

    @PutMapping
    public ResponseEntity<AlbumResponse> updateAlbum(
            @RequestBody @Validated(OnUpdate.class) AlbumRequest request
    ) {
        return ResponseEntity.ok(albumService.updateAlbum(request));
    }

    @PutMapping("/approved/{album-alias}")
    public ResponseEntity<Void> approvedAlbum(
            @PathVariable("album-alias") String alias
    ) {
        albumService.approvedAlbum(alias);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/rejected/{album-alias}")
    public ResponseEntity<Void> rejectedAlbum(
            @PathVariable("album-alias") String alias
    ) {
        albumService.rejectedAlbum(alias);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/released/{album-alias}")
    public ResponseEntity<Void> releasedAlbum(
            @PathVariable("album-alias") String alias
    ) {
        albumService.releasedAlbum(alias);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{album-id}")
    public ResponseEntity<Void> deleteAlbum(
            @PathVariable("album-id") UUID albumId
    ) {
        albumService.deleteAlbum(albumId);
        return ResponseEntity.noContent().build();
    }


}
