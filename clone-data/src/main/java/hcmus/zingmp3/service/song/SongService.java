package hcmus.zingmp3.service.song;

import hcmus.zingmp3.dto.song.SongRequest;
import hcmus.zingmp3.dto.song.SongResponse;

import java.util.UUID;

public interface SongService {

    SongResponse createSong(SongRequest songRequest);

    SongResponse getOrCreateIfNotExist(SongRequest songRequest);

    SongResponse getSongByAlias(String songAlias);

    void deleteSong(UUID songId);
}
