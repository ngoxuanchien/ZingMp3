package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.domain.model.Genre;
import hcmus.zingmp3.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.repository.elasticsearch.GenreRepository;
import lombok.RequiredArgsConstructor;
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
    public List<Genre> getAll() {
        return List.of();
    }

    @Override
    public List<Genre> getAllByNameContaining(String name) {
        return repository.findAllByNameContaining(name);
    }
}
