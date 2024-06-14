package hcmus.zingmp3.service.playlist;

import hcmus.zingmp3.domain.model.Playlist;
import hcmus.zingmp3.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.repository.elasticsearch.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PlaylistQueryServiceImpl implements PlaylistQueryService {

    private final PlaylistRepository repository;

    @Override
    public Playlist getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Playlist with id %s not found", id)
                ));
    }

    @Override
    public List<Playlist> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .toList();
    }
}
