package zingmp3.hcmus.playlistservice.playlist;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zingmp3.hcmus.playlistservice.exception.PlaylistNotFoundException;
import zingmp3.hcmus.playlistservice.playlist.model.Playlist;
import zingmp3.hcmus.playlistservice.playlist.model.PlaylistRequest;
import zingmp3.hcmus.playlistservice.playlist.model.PlaylistResponse;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {


    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;

    @Override
    public PlaylistResponse createPlaylist(PlaylistRequest request) {
        return playlistMapper.toDTO(playlistRepository.save(playlistMapper.toEntity(request)));
    }

    @Override
    public PlaylistResponse getPlaylistById(UUID id) {
        return playlistRepository.findById(id)
                .map(playlistMapper::toDTO)
                .orElseThrow(() -> new PlaylistNotFoundException(id.toString()));
    }

    @Override
    public PlaylistResponse updatePlaylist(UUID id, PlaylistRequest playlistRequest) {
        return playlistRepository.findById(id)
                .map(playlist -> {
                    Playlist newPlaylist = playlistMapper.toEntity(playlistRequest);
                    newPlaylist.setId(id);
                    return playlistMapper.toDTO(playlistRepository.save(playlist));
                })
                .orElseThrow(() -> new PlaylistNotFoundException(String.valueOf(id)));
    }

    @Override
    public void deletePlaylist(UUID id) {
        playlistRepository.findById(id)
                .ifPresent(playlistRepository::delete);
    }

    @Override
    public List<PlaylistResponse> getAllPlaylists(Pageable pageable) {
        return playlistRepository.findAll(pageable)
                .map(playlistMapper::toDTO)
                .toList();
    }
}
