package hcmus.zingmp3.service.song;

import java.util.UUID;

public interface SongService {
    boolean isExist(UUID songId);
}
