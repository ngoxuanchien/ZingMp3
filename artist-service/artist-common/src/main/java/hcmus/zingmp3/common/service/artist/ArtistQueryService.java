package hcmus.zingmp3.common.service.artist;

import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.common.domain.model.ArtistStatus;
import hcmus.zingmp3.common.service.QueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArtistQueryService extends QueryService<Artist> {

    Page<Artist> searchArtist(String name, List<ArtistStatus> status, Pageable pageable);

    Page<Artist> getAll(List<ArtistStatus> status, Pageable pageable);
}
