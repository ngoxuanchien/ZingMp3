package hcmus.zingmp3.song;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/songs")
public class SongController {
    private final SongService songService;

    @PostMapping
    public ResponseEntity<SongRestResponse> createSong(
            @Valid
            @RequestBody SongRestRequest songRequest
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(songService.createSong(songRequest));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SongRestResponse> updateSong(
            @PathVariable UUID id,
            @RequestPart("song") @Valid SongRestRequest songRequest,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail,
            @RequestPart(value = "audios", required = false) MultipartFile[] audios
    ) {
        mergeRequest(songRequest, thumbnail, audios);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(songService.updateSong(id, songRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable UUID id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }

    private void mergeRequest(SongRestRequest request, MultipartFile thumbnail, MultipartFile[] audios) {
        // merge request
        if (thumbnail != null) {
            request.setThumbnailFile(thumbnail);
        }

        if (audios != null && audios.length > 0) {
            request.setAudioFiles(new HashSet<>(Arrays.asList(audios)));
        }
    }
}
