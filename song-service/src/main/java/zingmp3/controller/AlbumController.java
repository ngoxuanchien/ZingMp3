package zingmp3.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zingmp3.dto.AlbumDto;
import zingmp3.service.AlbumService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;



    @GetMapping
    public ResponseEntity<List<AlbumDto>> findAll() {
        return ResponseEntity.ok(albumService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(albumService.findById(id));
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public ResponseEntity<List<AlbumDto>> findAll(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize
    ) {
        return ResponseEntity.ok(albumService.findAll(pageNumber, pageSize));
    }

    @PostMapping
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<AlbumDto> create(@RequestBody AlbumDto albumDto) {
        return ResponseEntity.ok(albumService.create(albumDto));
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<AlbumDto> update(@PathVariable UUID id, @RequestBody AlbumDto albumDto) {
        return ResponseEntity.ok(albumService.update(id, albumDto));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        albumService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
