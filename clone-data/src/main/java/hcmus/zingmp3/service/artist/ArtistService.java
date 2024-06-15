package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.dto.artist.ArtistRequest;
import hcmus.zingmp3.dto.artist.ArtistResponse;

import java.util.UUID;

public interface ArtistService {
    ArtistResponse createArtist(ArtistRequest artistRequest);

    void deleteArtist(UUID artistId);
}
