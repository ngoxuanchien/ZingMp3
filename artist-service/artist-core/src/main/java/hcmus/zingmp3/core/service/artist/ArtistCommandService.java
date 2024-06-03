package hcmus.zingmp3.core.service.artist;

import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.core.service.CommandService;

public interface ArtistCommandService extends CommandService<Artist> {
    void update(Artist object);
    void delete(Artist object);
}
