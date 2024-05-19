package zingmp3.hcmus.playlistservice.playlist;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import zingmp3.hcmus.playlistservice.playlist.model.PlaylistRequest;
import zingmp3.hcmus.playlistservice.playlist.model.PlaylistResponse;

import java.util.List;
import java.util.UUID;

@Service
public interface PlaylistService {
    PlaylistResponse createPlaylist(PlaylistRequest playlistRequest);
    PlaylistResponse getPlaylistById(UUID id);
    PlaylistResponse updatePlaylist(UUID id, PlaylistRequest playlistRequest);
    void deletePlaylist(UUID id);
    List<PlaylistResponse> getAllPlaylists(Pageable pageable);
}
