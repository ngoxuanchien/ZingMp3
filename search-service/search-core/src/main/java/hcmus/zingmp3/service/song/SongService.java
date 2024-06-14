package hcmus.zingmp3.service.song;

import hcmus.zingmp3.web.model.response.SongResponse;

import java.util.List;
import java.util.UUID;

public interface SongService {
    SongResponse getSongById(UUID id);

    List<SongResponse> getAllSongs();

    List<SongResponse> getAllSongsByTitle(String title);
}
