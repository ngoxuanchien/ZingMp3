package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.web.model.response.ArtistResponse;

import java.util.List;
import java.util.UUID;

public interface ArtistService {
    ArtistResponse getArtistById(UUID id);

    List<ArtistResponse> getAllArtists();

    List<ArtistResponse> getAllArtistsByName(String name);
}
