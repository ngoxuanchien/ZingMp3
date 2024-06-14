package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.domain.model.Artist;
import hcmus.zingmp3.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.repository.elasticsearch.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ArtistQueryServiceImpl implements ArtistQueryService {

    private final ArtistRepository repository;

    @Override
    public Artist getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Artist with id %s not found", id)
                ));
    }

    @Override
    public List<Artist> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .toList();
    }

    @Override
    public List<Artist> getAllByName(String name) {
        return repository.findAllByNameContaining(name);
    }
}
