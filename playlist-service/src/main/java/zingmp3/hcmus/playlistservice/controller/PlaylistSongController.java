package zingmp3.hcmus.playlistservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.dto.record.AddSongRequest;
import zingmp3.hcmus.playlistservice.dto.SongDTO;
import zingmp3.hcmus.playlistservice.dto.record.RemoveSongRequest;
import zingmp3.hcmus.playlistservice.service.SongService;

import java.time.Duration;

import static org.springframework.http.MediaType.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/playlist/song")
public class PlaylistSongController {

    private final SongService songService;

    @GetMapping("/{playlistId}")
    public Flux<SongDTO> findAllByPlaylistId(
            @PathVariable String playlistId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return songService.findAllSongByPlaylistId(playlistId, pageable)
                .doOnError(Throwable::printStackTrace);
    }
}
