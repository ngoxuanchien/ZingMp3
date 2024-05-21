package hcmus.zingmp3.music_service.artist;

import hcmus.zingmp3.music_service.artist.model.Artist;
import hcmus.zingmp3.music_service.artist.model.ArtistRequest;
import hcmus.zingmp3.music_service.artist.model.ArtistResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ArtistService {
    ArtistResponse createArtist(ArtistRequest request);
    ArtistResponse getArtistById(String id);
    List<ArtistResponse> getAllArtists(Pageable pageable);
    ArtistResponse updateArtist(String id, ArtistRequest request);
    void deleteArtist(String id);
    Artist getOrCreateByAlias(String alias);
}
