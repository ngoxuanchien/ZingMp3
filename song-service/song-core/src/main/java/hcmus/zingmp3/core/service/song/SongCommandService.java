package hcmus.zingmp3.core.service.song;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.core.service.CommandService;

public interface SongCommandService extends CommandService<Song> {
    void update(Song object);
    void delete(Song object);
}
