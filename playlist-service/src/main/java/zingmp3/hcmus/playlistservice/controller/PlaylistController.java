package zingmp3.hcmus.playlistservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zingmp3.hcmus.playlistservice.dto.playlist.PlaylistCreationDTO;
import zingmp3.hcmus.playlistservice.dto.playlist.PlaylistDTO;
import zingmp3.hcmus.playlistservice.dto.playlist.PlaylistInListDTO;
import zingmp3.hcmus.playlistservice.dto.song.NewSongDTO;
import zingmp3.hcmus.playlistservice.service.PlaylistService;

import java.util.List;

@RestController
@RequestMapping("/api/playlist")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping("/{playlistId}")
    public ResponseEntity<PlaylistDTO> getPlaylist(@PathVariable long playlistId) {
        return ResponseEntity.status(HttpStatus.OK).body(playlistService.getPlaylist(playlistId));
    }

    @GetMapping
    public ResponseEntity<List<PlaylistInListDTO>> getAllPlaylist() {
        return ResponseEntity.status(HttpStatus.OK).body(playlistService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody PlaylistCreationDTO playlistDTO) {
        playlistService.create(playlistDTO);
    }

    @PutMapping("/{playlistId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long playlistId, @RequestBody PlaylistCreationDTO playlistDTO) {
        playlistService.update(playlistId, playlistDTO);
    }

    @PostMapping("/{playlistId}/songs")
    @ResponseStatus(HttpStatus.OK)
    public void addSong(@PathVariable long playlistId, @RequestBody NewSongDTO song) {
        playlistService.addSong(playlistId, song.getSongId());
    }

    @DeleteMapping ("/{playlistId}/songs/{songId}")
    @ResponseStatus(HttpStatus.OK)
    public void addSong(@PathVariable long playlistId, @PathVariable long songId) {
        playlistService.removeSong(playlistId, songId);
    }

    @DeleteMapping("/{playlistId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlaylist(@PathVariable long playlistId) {
        playlistService.deletePlaylist(playlistId);
    }
}
