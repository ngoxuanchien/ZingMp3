package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.service.CommandService;

public interface ArtistCommandService extends CommandService<Artist> {
    void approve(Artist artist);
    void reject(Artist artist);
}
