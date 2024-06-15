package hcmus.zingmp3.service.playlist;

import com.google.gson.JsonObject;
import hcmus.zingmp3.dto.playlist.PlaylistRequest;
import hcmus.zingmp3.dto.playlist.PlaylistResponse;
import hcmus.zingmp3.mapper.PlaylistMapper;

import java.util.UUID;

public class PlaylistCloneServiceImpl implements PlaylistCloneService {

    private static final PlaylistService playlistService = new PlaylistServiceImpl();
    private static final PlaylistMapper playlistMapper = new PlaylistMapper();

    @Override
    public UUID clonePlaylist(JsonObject jsonObject) {

        PlaylistRequest request = playlistMapper.toRequest(jsonObject);

        PlaylistResponse response = playlistService.getOrCreateIfNotExist(request);

        return response.id();

    }
}
