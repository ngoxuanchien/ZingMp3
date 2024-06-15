package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.dto.artist.ArtistRequest;

import java.util.UUID;

public interface ArtistService {
    UUID createArtist(ArtistRequest artistRequest);

    void deleteArtist(UUID artistId);
}
