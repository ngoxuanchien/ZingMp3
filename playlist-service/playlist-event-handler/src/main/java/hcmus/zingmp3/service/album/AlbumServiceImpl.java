package hcmus.zingmp3.service.album;

import hcmus.zingmp3.common.domain.model.Album;
import hcmus.zingmp3.common.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository repository;

    @Override
    public void create(Album album) {
        repository.save(album);
    }

    @Override
    public void update(Album album) {
        repository.save(album);
    }

    @Override
    public void delete(Album album) {
        repository.delete(album);
    }
}
