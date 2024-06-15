package hcmus.zingmp3.service.song;

import hcmus.zingmp3.dto.song.SongRequest;

import java.util.UUID;

public interface SongService {

    UUID createSong(SongRequest songRequest);

    void deleteSong(UUID songId);
}
