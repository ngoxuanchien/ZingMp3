package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.common.domain.model.Artist;

import java.util.UUID;

public interface ArtistService {
    void create(Artist artist);
    void update(Artist artist);
    void delete(Artist artist);
}
