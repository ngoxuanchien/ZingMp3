package hcmus.zingmp3.common.service.song;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.domain.model.SongStatus;
import hcmus.zingmp3.common.service.QueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SongQueryService extends QueryService<Song> {
    Page<Song> getAllByCreatedBy(UUID userId, Pageable pageable);

    Page<Song> searchSong(String name, Pageable pageable);

    Page<Song> searchMySongs(String name, List<SongStatus> status, UUID userId, Pageable pageable);

    Page<Song> getAllMySongs(List<SongStatus> status, UUID userId, Pageable pageable);
}
