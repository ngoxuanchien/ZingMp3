package hcmus.zingmp3.songevent;

import hcmus.zingmp3.song.Song;
import org.springframework.stereotype.Service;

public interface SongEventService {
    void createSong(Song song);
    void updateSong(Song song);
    void deleteSong(Song song);
}
