package hcmus.zingmp3.common.service.playlist;

import hcmus.zingmp3.common.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.common.domain.model.Playlist;
import hcmus.zingmp3.common.repository.AbstractPlaylistRepository;
import hcmus.zingmp3.common.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaylistQueryServiceImpl implements PlaylistQueryService {

    private final PlaylistRepository repository;

    private final AbstractPlaylistRepository abstractPlaylistRepository;

    @Override
    public Playlist getByAlias(String alias) {
        return repository.findByAlias(alias)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Playlist with alias %s not found", alias)
                ));
    }

    @Override
    public Playlist getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Playlist with id %s not found", id)
                ));
    }

    @Override
    public boolean existsByAlias(String alias) {
        return abstractPlaylistRepository.existsByAlias(alias);
    }

    @Override
    public List<Playlist> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Override
    public List<Playlist> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Playlist> getMyPlaylists(UUID userId, Pageable pageable) {
        return repository.findAllByCreatedBy(userId, pageable);
    }

    @Override
    public List<Playlist> searchPlaylist(String title, Pageable pageable) {
        return repository.findAllByTitleContaining(title, pageable);
    }
}
