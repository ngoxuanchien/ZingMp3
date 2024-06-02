package hcmus.zingmp3.artistEvent;

import hcmus.zingmp3.artist.Artist;

import java.util.List;

public interface ArtistEventService {
    void createArtist(Artist artist);
    void updateArtist(Artist artist);
    void deleteArtist(Artist artist);

    List<ArtistEvent> getAllArtistEvents();
}
