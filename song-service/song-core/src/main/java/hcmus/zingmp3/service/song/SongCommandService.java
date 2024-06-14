package hcmus.zingmp3.service.song;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.service.CommandService;

public interface SongCommandService extends CommandService<Song> {
    void approved(Song object);
    void rejected(Song object);
    void released(Song object);
}
