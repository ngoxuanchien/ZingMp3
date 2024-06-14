package hcmus.zingmp3.common.service.playlist;

import hcmus.zingmp3.common.domain.model.Playlist;
import hcmus.zingmp3.common.service.QueryService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PlaylistQueryService extends QueryService<Playlist> {
    List<Playlist> getMyPlaylists(UUID userId, Pageable pageable);

    List<Playlist> searchPlaylist(String name, Pageable pageable);
}
