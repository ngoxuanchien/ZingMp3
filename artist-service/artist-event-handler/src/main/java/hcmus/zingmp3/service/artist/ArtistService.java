package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.common.domain.model.Artist;

import java.util.UUID;

public interface ArtistService {
    Artist create(Artist artist);
    Artist update(Artist artist);
    void delete(Artist artist);
}
