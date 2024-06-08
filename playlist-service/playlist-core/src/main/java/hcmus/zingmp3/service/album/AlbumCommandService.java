package hcmus.zingmp3.service.album;

import hcmus.zingmp3.common.domain.model.Album;
import hcmus.zingmp3.service.CommandService;

public interface AlbumCommandService extends CommandService<Album> {
    void approve(Album object);
    void reject(Album object);
    void release(Album object);
}
