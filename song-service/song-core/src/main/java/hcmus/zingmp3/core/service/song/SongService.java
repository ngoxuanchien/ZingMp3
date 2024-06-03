package hcmus.zingmp3.core.service.song;

import hcmus.zingmp3.common.service.song.SongQueryService;
import hcmus.zingmp3.core.web.dto.SongRequest;
import hcmus.zingmp3.core.web.dto.SongResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SongService extends SongCommandService, SongQueryService {
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
}
