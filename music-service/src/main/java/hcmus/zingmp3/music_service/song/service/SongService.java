package hcmus.zingmp3.music_service.song.service;

import hcmus.zingmp3.music_service.song.model.Song;
import hcmus.zingmp3.music_service.song.model.SongRequest;
import hcmus.zingmp3.music_service.song.model.SongResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface SongService {
    SongResponse createSong(SongRequest request);
    SongResponse getSongById(UUID id);
    List<SongResponse> getAllSongs(Pageable pageable);
    SongResponse updateSong(UUID id, SongRequest request);
    void deleteSong(UUID id);
    Song getByAlias(String alias);
}
