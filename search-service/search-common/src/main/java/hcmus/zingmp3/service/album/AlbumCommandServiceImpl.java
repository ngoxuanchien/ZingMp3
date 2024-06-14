package hcmus.zingmp3.service.album;

import hcmus.zingmp3.domain.model.Album;
import hcmus.zingmp3.repository.elasticsearch.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumCommandServiceImpl implements AlbumCommandService {

    private final AlbumRepository repository;

    @Override
    public Album create(Album object) {
        return repository.save(object);
    }

    @Override
    public Album update(Album object) {
        return repository.save(object);
    }

    @Override
    public void delete(Album object) {
        repository.delete(object);
    }
}
