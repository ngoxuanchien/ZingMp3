package hcmus.zingmp3.service.playlist;

import hcmus.zingmp3.common.domain.model.Playlist;
import hcmus.zingmp3.common.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository repository;

    @Override
    public void create(Playlist playlist) {
        repository.save(playlist);
    }

    @Override
    public void update(Playlist playlist) {
        repository.save(playlist);
    }

    @Override
    public void delete(Playlist playlist) {
        repository.delete(playlist);
    }
}
