package zingmp3.hcmus.playlistservice.artist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {

    @GetMapping
    public String getArtist() {
        return "Artist";
    }
}
