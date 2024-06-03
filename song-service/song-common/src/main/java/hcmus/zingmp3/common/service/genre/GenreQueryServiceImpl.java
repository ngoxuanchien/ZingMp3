package hcmus.zingmp3.common.service.genre;

import hcmus.zingmp3.common.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.common.domain.model.Genre;
import hcmus.zingmp3.common.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenreQueryServiceImpl implements GenreQueryService {
    private final GenreRepository repository;
    @Override
    public Genre getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Genre with id %s not found", id)
                ));
    }

    @Override
    public Genre getByAlias(String alias) {
        return repository.findByAlias(alias)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Genre with alias %s not found", alias)
                ));
    }

    @Override
    public boolean existsByAlias(String alias) {
        return repository.existsByAlias(alias);
    }

    @Override
    public List<Genre> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Override
    public List<Genre> getAll() {
        return repository.findAll();
    }
}
