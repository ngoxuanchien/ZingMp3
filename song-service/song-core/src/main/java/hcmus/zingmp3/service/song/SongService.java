package hcmus.zingmp3.service.song;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.domain.model.SongStatus;
import hcmus.zingmp3.common.service.QueryService;
import hcmus.zingmp3.service.CommandService;
import hcmus.zingmp3.web.dto.SongRequest;
import hcmus.zingmp3.web.dto.SongResponse;
import org.springframework.data.domain.Page;
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

    Page<SongResponse> getAllSongs(Pageable pageable);

    List<SongResponse> getAllSongs();

    Page<SongResponse> getAllMySongs(Pageable pageable);

    Page<SongResponse> searchSong(String title, Pageable pageable);

    Page<SongResponse> searchMySongs(String name, List<SongStatus> status, Pageable pageable);

    Page<SongResponse> getAllMySongs(List<SongStatus> status, Pageable pageable);
}
