package hcmus.zingmp3.common.service.song;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.service.QueryService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SongQueryService extends QueryService<Song> {
    List<Song> getAllByCreatedBy(UUID userId, Pageable pageable);

    List<Song> searchSong(String name, Pageable pageable);
}
