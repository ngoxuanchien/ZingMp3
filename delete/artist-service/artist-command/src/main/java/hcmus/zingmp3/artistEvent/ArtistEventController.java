package hcmus.zingmp3.artistEvent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artist/event")
public class ArtistEventController {
    private final ArtistEventService artistEventService;

    @GetMapping
    public ResponseEntity<List<ArtistEvent>> getArtistEvent() {
        return ResponseEntity.ok(artistEventService.getAllArtistEvents());
    }
}
