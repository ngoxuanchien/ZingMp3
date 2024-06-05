package hcmus.zingmp3.service.song;

import hcmus.zingmp3.common.domain.model.Song;

public interface SongService {
    Song create(Song song);
    void update(Song song);
    void delete(Song song);
}
