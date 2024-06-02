package hcmus.zingmp3.song;

import java.util.UUID;

public interface SongService {

    SongRestResponse createSong(SongRestRequest request);

    SongRestResponse updateSong(UUID id, SongRestRequest request);

    void deleteSong(UUID id);
}
