package hcmus.zingmp3.service.playlist;

import hcmus.zingmp3.common.domain.model.Playlist;

public interface PlaylistService {
    void create(Playlist playlist);
    void update(Playlist playlist);
    void delete(Playlist playlist);
}
