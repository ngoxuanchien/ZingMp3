package hcmus.zingmp3.song;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface SongService {
    List<SongRestResponse> getAllSongs();

    SongRestResponse getSongById(UUID songId);

    List<SongRestResponse> getAllSongs(Pageable pageable);

    void createSong(Song song);

    void updateSong(Song song);

    void deleteSong(UUID id);
}
