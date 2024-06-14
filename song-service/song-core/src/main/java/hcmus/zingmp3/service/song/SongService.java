package hcmus.zingmp3.service.song;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.service.QueryService;
import hcmus.zingmp3.service.CommandService;
import hcmus.zingmp3.web.dto.SongRequest;
import hcmus.zingmp3.web.dto.SongResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SongService {
    SongResponse createSong(SongRequest request);

    SongResponse updateSong(SongRequest request);

    void deleteSong(UUID id);

    SongResponse getSongById(UUID id);

    SongResponse getSongByAlias(String alias);

    void approvedSong(String alias);

    void rejectedSong(String alias);

    void releasedSong(String alias);

    List<SongResponse> getAllSongs(Pageable pageable);

    List<SongResponse> getAllSongs();

    List<SongResponse> getAllMySongs(Pageable pageable);

    List<SongResponse> searchSong(String title, Pageable pageable);

    List<SongResponse> searchMySongs(String name, Pageable pageable);
}
