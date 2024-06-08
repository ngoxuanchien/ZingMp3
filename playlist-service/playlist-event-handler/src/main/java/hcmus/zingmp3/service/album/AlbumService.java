package hcmus.zingmp3.service.album;

import hcmus.zingmp3.common.domain.model.Album;

public interface AlbumService {
    void create(Album album);
    void update(Album album);
    void delete(Album album);
}
