package hcmus.zingmp3.service.album;

import hcmus.zingmp3.domain.model.Album;
import hcmus.zingmp3.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.repository.elasticsearch.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AlbumQueryServiceImpl implements AlbumQueryService {

    private final AlbumRepository repository;

    @Override
    public Album getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Album with id %s not found", id)
                ));
    }

    @Override
    public List<Album> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .toList();
    }

    @Override
    public List<Album> getAlbumsByTitle(String title) {
        return repository.findByTitleContaining(title);
    }
}
