package hcmus.zingmp3.service.playlist;

import hcmus.zingmp3.dto.playlist.PlaylistRequest;
import hcmus.zingmp3.dto.playlist.PlaylistResponse;

import java.util.UUID;

public interface PlaylistService {

    PlaylistResponse createPlaylist(PlaylistRequest playlistRequest);

    PlaylistResponse getOrCreateIfNotExist(PlaylistRequest playlistRequest);

    PlaylistResponse getPlaylistByAlias(String playlistAlias);

    void deletePlaylist(UUID playlistId);

}
