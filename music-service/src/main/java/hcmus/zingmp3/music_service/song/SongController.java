package hcmus.zingmp3.music_service.song;

import hcmus.zingmp3.music_service.song.model.SongRequest;
import hcmus.zingmp3.music_service.song.model.SongResponse;
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

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/music/song/")
public class SongController {
    private final SongService songService;
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @RequestBody(
            content = @Content(encoding = {
            @Encoding(name = "song", contentType = MediaType.APPLICATION_JSON_VALUE),
            @Encoding(name = "artists", contentType = MediaType.APPLICATION_JSON_VALUE),
            @Encoding(name = "genres", contentType = MediaType.APPLICATION_JSON_VALUE),
            @Encoding(name = "composers", contentType = MediaType.APPLICATION_JSON_VALUE)
    }))
    public ResponseEntity<SongResponse> createSong(
            @RequestPart("song") @Valid SongRequest request,
            @RequestPart(value = "thumbnailFile", required = false) MultipartFile thumbnailFile,
            @RequestPart(value = "audioFiles", required = false) MultipartFile[] audioFiles,
            @RequestPart(value = "artists", required = false) Set<String> artists,
            @RequestPart(value = "genres", required = false) Set<String> genres,
            @RequestPart(value = "composers", required = false) Set<String> composers
    ) {
        request.addAllArtists(artists);
        request.addAllGenres(genres);
        request.addAllComposers(composers);
        request.addAudioFiles(audioFiles);
        request.setThumbnailFile(thumbnailFile);

        return ResponseEntity.ok(songService.createSong(request));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<SongResponse> getSongById(@PathVariable UUID id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

    @GetMapping
    public ResponseEntity<List<SongResponse>> getAllSongs(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) Integer size,
            @Schema(description = "Sort by field", allowableValues = {"id", "title", "alias", "artistsNames", "duration", "releaseDate", "likes", "listen", "comment", "createdAt", "modifiedAt"})
            @RequestParam(defaultValue = "id") String sort,
            @Schema(description = "Sort direction", allowableValues = {"asc", "desc"})
            @RequestParam(defaultValue = "asc") String order
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(order), sort);
        Pageable pageable = PageRequest.of(page, size, sortData);
        return ResponseEntity.ok(songService.getAllSongs(pageable));
    }

    @PutMapping(
            value = "{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @RequestBody(content = @Content(encoding = {
            @Encoding(name = "song", contentType = MediaType.APPLICATION_JSON_VALUE),
            @Encoding(name = "artists", contentType = MediaType.APPLICATION_JSON_VALUE),
            @Encoding(name = "genres", contentType = MediaType.APPLICATION_JSON_VALUE),
            @Encoding(name = "composers", contentType = MediaType.APPLICATION_JSON_VALUE)
    }))
    public ResponseEntity<SongResponse> updateSong(
            @PathVariable UUID id,
            @RequestPart("song") @Valid SongRequest request,
            @RequestPart(value = "thumbnailFile", required = false) MultipartFile thumbnailFile,
            @RequestPart(value = "audioFiles", required = false) MultipartFile[] audioFiles,
            @RequestPart(value = "artists", required = false) Set<String> artists,
            @RequestPart(value = "genres", required = false) Set<String> genres,
            @RequestPart(value = "composers", required = false) Set<String> composers
    ) {
        request.addAllArtists(artists);
        request.addAllGenres(genres);
        request.addAllComposers(composers);
        request.addAudioFiles(audioFiles);
        request.setThumbnailFile(thumbnailFile);
        return ResponseEntity.ok(songService.updateSong(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSong(
            @PathVariable UUID id
    ) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }
}
