package hcmus.zingmp3.service.song;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository repository;

    @Override
    public Song create(Song song) {
        return repository.save(song);
    }

    @Override
    public void update(Song song) {
        repository.save(song);
    }

    @Override
    public void delete(Song song) {
        repository.delete(song);
    }
}
