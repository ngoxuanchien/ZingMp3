package hcmus.zingmp3.service.song;

import hcmus.zingmp3.domain.model.Song;
import hcmus.zingmp3.repository.elasticsearch.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongCommandServiceImpl implements SongCommandService {
    private final SongRepository repository;

    @Override
    public Song create(Song object) {
        return repository.save(object);
    }

    @Override
    public Song update(Song object) {
        return repository.save(object);
    }

    @Override
    public void delete(Song object) {
        repository.delete(object);
    }
}
