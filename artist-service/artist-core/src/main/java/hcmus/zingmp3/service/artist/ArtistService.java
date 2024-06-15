package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.common.domain.model.ArtistStatus;
import hcmus.zingmp3.common.service.QueryService;
import hcmus.zingmp3.service.CommandService;
import hcmus.zingmp3.web.dto.ArtistRequest;
import hcmus.zingmp3.web.dto.ArtistResponse;
import org.springframework.data.domain.Page;
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

    Page<ArtistResponse> getAllArtists(Pageable pageable);

    List<ArtistResponse> getAllArtists();

    Page<ArtistResponse> searchArtist(String name, List<ArtistStatus> status, Pageable pageable);

    Page<ArtistResponse> getAllArtists(List<ArtistStatus> status, Pageable pageable);
}
