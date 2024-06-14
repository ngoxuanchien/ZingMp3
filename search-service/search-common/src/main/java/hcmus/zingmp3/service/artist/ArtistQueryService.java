package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.domain.model.Artist;
import hcmus.zingmp3.service.QueryService;

import java.util.List;

public interface ArtistQueryService extends QueryService<Artist> {

    List<Artist> getAllByName(String name);
}
