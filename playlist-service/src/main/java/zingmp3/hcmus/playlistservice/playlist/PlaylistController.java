package zingmp3.hcmus.playlistservice.playlist;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zingmp3.hcmus.playlistservice.playlist.model.PlaylistRequest;
import zingmp3.hcmus.playlistservice.playlist.model.PlaylistResponse;
import zingmp3.hcmus.playlistservice.validation.ValuesAllowed;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
//@Validated
@RequestMapping("/api/playlist")
public class PlaylistController {
//    private static final String DEFAULT_PAGE = "0";
//    private static final String DEFAULT_SIZE = "10";
//    private static final String DEFAULT_SORT = "id";
//    private static final String DEFAULT_DIRECTION = "asc";
//
    private final PlaylistService playlistService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RequestBody(content = @Content(encoding = {
            @Encoding(name = "playlist", contentType = MediaType.APPLICATION_JSON_VALUE),
            @Encoding(name = "genres", contentType = MediaType.APPLICATION_JSON_VALUE),
            @Encoding(name = "artists", contentType = MediaType.APPLICATION_JSON_VALUE),
            @Encoding(name = "songs", contentType = MediaType.APPLICATION_JSON_VALUE),
    }))
    public ResponseEntity<PlaylistResponse> createPlaylist(
            @RequestPart(name = "playlist") @Valid PlaylistRequest request,
            @RequestPart(name = "thumbnailFile", required = false) MultipartFile thumbnailFile,
            @RequestPart(name = "genres", required = false) Set<String> genres,
            @RequestPart(name = "artists", required = false) Set<String> artists,
            @RequestPart(name = "songs", required = false) Set<String> songs
    ) {
        request.addAllGenre(genres).addAllArtist(artists).addAllSong(songs).setThumbnailFile(thumbnailFile);
        return ResponseEntity.ok(playlistService.createPlaylist(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<PlaylistResponse> getPlaylistById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(playlistService.getPlaylistById(id));
    }

    @GetMapping
    public ResponseEntity<List<PlaylistResponse>> getAllPlaylists(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
//            @ValuesAllowed(propName = "orderBy",values = {"id", "title", "releasedAt", "description", "createdDate", "updatedDate"})
            @Schema(allowableValues = {"id", "title", "releasedAt", "description", "createdDate", "updatedDate"})
            @RequestParam(name = "orderBy", defaultValue = "id") String orderBy,
//            @ValuesAllowed(propName = "sortDirection",values = {"asc", "desc"})
            @Schema(allowableValues = {"asc", "desc"})
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection
    ) {
        Sort sortObj = Sort.by(Sort.Direction.fromString(sortDirection), orderBy);
        Pageable pageable = PageRequest.of(page, size, sortObj);
        return ResponseEntity.ok(playlistService.getAllPlaylists(pageable));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RequestBody(content = @Content(encoding = {
            @Encoding(name = "playlist", contentType = MediaType.APPLICATION_JSON_VALUE),
            @Encoding(name = "genres", contentType = MediaType.APPLICATION_JSON_VALUE),
            @Encoding(name = "artists", contentType = MediaType.APPLICATION_JSON_VALUE),
            @Encoding(name = "songs", contentType = MediaType.APPLICATION_JSON_VALUE),
    }))
    public ResponseEntity<PlaylistResponse> updatePlaylist(
            @PathVariable UUID id,
            @RequestBody @Valid PlaylistRequest request,
            @RequestPart(name = "thumbnailFile", required = false) MultipartFile thumbnailFile,
            @RequestPart(name = "genres", required = false) Set<String> genres,
            @RequestPart(name = "artists", required = false) Set<String> artists,
            @RequestPart(name = "songs", required = false) Set<String> songs
    ) {
        request.addAllGenre(genres)
                .addAllArtist(artists)
                .addAllSong(songs)
                .setThumbnailFile(thumbnailFile);
        return ResponseEntity.ok(playlistService.updatePlaylist(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(
            @PathVariable UUID id
    ) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }

}
