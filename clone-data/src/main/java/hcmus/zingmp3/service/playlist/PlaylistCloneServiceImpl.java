package hcmus.zingmp3.service.playlist;

import com.google.gson.JsonObject;
import hcmus.zingmp3.dto.playlist.PlaylistRequest;
import hcmus.zingmp3.dto.playlist.PlaylistResponse;
import hcmus.zingmp3.mapper.PlaylistMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static hcmus.zingmp3.Main.playlistMapper;
import static hcmus.zingmp3.Main.playlistService;

@Service
public class PlaylistCloneServiceImpl implements PlaylistCloneService {

    @Override
    public UUID clonePlaylist(JsonObject jsonObject) {

        PlaylistRequest request = playlistMapper.toRequest(jsonObject);

        PlaylistResponse response = playlistService.getOrCreateIfNotExist(request);

        return response.id();

    }
}
