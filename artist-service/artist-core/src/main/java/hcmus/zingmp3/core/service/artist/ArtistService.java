package hcmus.zingmp3.core.service.artist;

import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.common.service.QueryService;
import hcmus.zingmp3.common.service.artist.ArtistQueryService;
import hcmus.zingmp3.core.service.CommandService;
import hcmus.zingmp3.core.web.dto.ArtistRequest;
import hcmus.zingmp3.core.web.dto.ArtistResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ArtistService extends QueryService<Artist>, CommandService<Artist> {
    ArtistResponse createArtist(ArtistRequest request);

    ArtistResponse updateArtist(ArtistRequest request);

    void deleteArtist(UUID id);

    ArtistResponse getArtistByAlias(String alias);

    void approveArtist(String alias);

    void rejectArtist(String alias);

    ArtistResponse getArtistById(UUID id);

    List<ArtistResponse> getAllArtists(Pageable pageable);

    List<ArtistResponse> getAllArtists();
}
