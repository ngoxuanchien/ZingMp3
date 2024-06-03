package hcmus.mp3.web.controller;

import hcmus.mp3.service.media.AudioService;
import hcmus.mp3.web.dto.AudioResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/audios")
public class AudioController {
    private final AudioService audioService;

    @PostMapping
    public ResponseEntity<AudioResponseDto> createAudio(
            @RequestPart("audio") final MultipartFile audio
    ) {
        return ResponseEntity.ok(audioService.createAudio(audio));
    }

    @GetMapping("/{audio-id}")
    public ResponseEntity<AudioResponseDto> getAudio(
            @PathVariable("audio-id") final UUID audioId
    ) {
        return ResponseEntity.ok(audioService.getAudio(audioId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AudioResponseDto>> getAllAudio() {
        return ResponseEntity.ok(audioService.getAllAudio());
    }

    @DeleteMapping("/{audio-id}")
    public ResponseEntity<Void> deleteAudio(
            @PathVariable("audio-id") final UUID audioId
    ) {
        audioService.deleteAudio(audioId);
        return ResponseEntity.noContent().build();
    }
}
