package hcmus.zingmp3.service.playlist;

import hcmus.zingmp3.common.domain.model.Playlist;
import hcmus.zingmp3.common.service.QueryService;
import hcmus.zingmp3.service.CommandService;
import hcmus.zingmp3.web.dto.PlaylistRequest;
import hcmus.zingmp3.web.dto.PlaylistResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PlaylistService extends CommandService<Playlist>, QueryService<Playlist> {
    PlaylistResponse createPlaylist(PlaylistRequest request);

    PlaylistResponse getPlaylistById(UUID id);

    PlaylistResponse getPlaylistByAlias(String alias);

    List<PlaylistResponse> getAllPlaylists();

    List<PlaylistResponse> getAllPlaylists(Pageable pageable);

    PlaylistResponse updatePlaylist(PlaylistRequest request);

    void deletePlaylist(UUID id);


    List<PlaylistResponse> getMyPlaylists(Pageable pageable);

    List<PlaylistResponse> searchPlaylist(String name, Pageable pageable);
}
