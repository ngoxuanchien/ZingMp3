package zingmp3.hcmus.playlistservice.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.dto.PlaylistDTO;
import zingmp3.hcmus.playlistservice.entity.PlaylistEntity;
import zingmp3.hcmus.playlistservice.service.PlaylistService;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/api/playlist")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get All Playlists") })
    public Flux<PlaylistDTO> getAllPlaylists(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return playlistService.findAll(pageable);
    }

    @GetMapping("/search")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "search Playlists") })
    public Flux<PlaylistDTO> searchPlaylists(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return playlistService.searchPlaylists(name, pageable);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Keycloak")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get Playlist by Id") })
    public Mono<ResponseEntity<PlaylistDTO>> getPlaylistById(@PathVariable String id) {
        return playlistService.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(
                        ResponseEntity
                                .notFound()
                                .build()
                );
    }

    @PostMapping
    @SecurityRequirement(name = "Keycloak")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "create Playlist") })
    public Mono<ResponseEntity<PlaylistDTO>> createPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        return playlistService.create(playlistDTO)
                .map(ResponseEntity::ok);
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "update Playlist") })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Keycloak")
    public Mono<ResponseEntity<PlaylistDTO>> updatePlaylist(@PathVariable String id, @RequestBody PlaylistDTO playlistDTO) {
        return playlistService.update(id, playlistDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(
                        ResponseEntity
                                .notFound()
                                .build()
                );
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "delete Playlist") })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Keycloak")
    public Mono<ResponseEntity<Void>> deletePlaylist(@PathVariable String id) {
        return playlistService.delete(id)
                .then(Mono.just(ResponseEntity.ok().build()));
    }

}
