package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.common.domain.model.Genre;
import hcmus.zingmp3.common.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository repository;

    @Override
    public Genre create(Genre genre) {
        return repository.save(genre);
    }

    @Override
    public void update(Genre genre) {
        repository.save(genre);
    }

    @Override
    public void delete(Genre genre) {
        repository.delete(genre);
    }
}
