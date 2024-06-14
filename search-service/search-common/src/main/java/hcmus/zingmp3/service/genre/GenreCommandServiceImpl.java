package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.domain.model.Genre;
import hcmus.zingmp3.repository.elasticsearch.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreCommandServiceImpl implements GenreCommandService {

    private final GenreRepository repository;

    @Override
    public Genre create(Genre object) {
        return repository.save(object);
    }

    @Override
    public Genre update(Genre object) {
        return repository.save(object);
    }

    @Override
    public void delete(Genre object) {
        repository.delete(object);
    }
}
