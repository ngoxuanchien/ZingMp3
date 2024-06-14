package hcmus.zingmp3.service.playlist;

import hcmus.zingmp3.web.model.response.PlaylistResponse;

import java.util.List;
import java.util.UUID;

public interface PlaylistService {
    PlaylistResponse getPlaylistById(UUID id);

    List<PlaylistResponse> getAllPlaylists();

    List<PlaylistResponse> getAllPlaylistsByTitle(String title);
}
