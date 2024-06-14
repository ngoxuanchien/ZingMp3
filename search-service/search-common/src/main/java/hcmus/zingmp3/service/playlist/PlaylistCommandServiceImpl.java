package hcmus.zingmp3.service.playlist;

import hcmus.zingmp3.domain.model.Playlist;
import hcmus.zingmp3.repository.elasticsearch.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistCommandServiceImpl implements PlaylistCommandService {

    private final PlaylistRepository repository;

    @Override
    public Playlist create(Playlist object) {
        return repository.save(object);
    }

    @Override
    public Playlist update(Playlist object) {
        return repository.save(object);
    }

    @Override
    public void delete(Playlist object) {
        repository.delete(object);
    }
}
