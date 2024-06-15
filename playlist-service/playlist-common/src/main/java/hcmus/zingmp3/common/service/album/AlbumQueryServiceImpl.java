package hcmus.zingmp3.common.service.album;

import hcmus.zingmp3.common.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.common.domain.model.Album;
import hcmus.zingmp3.common.domain.model.AlbumStatus;
import hcmus.zingmp3.common.repository.AbstractPlaylistRepository;
import hcmus.zingmp3.common.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlbumQueryServiceImpl implements AlbumQueryService {

    private final AlbumRepository repository;

    private final AbstractPlaylistRepository abstractPlaylistRepository;

    @Override
    public Album getByAlias(String alias) {
        return repository.findByAlias(alias)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Album with alias %s not found", alias)
                ));
    }

    @Override
    public Album getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Album with id %s not found", id)
                ));
    }

    @Override
    public boolean existsByAlias(String alias) {
        return abstractPlaylistRepository.existsByAlias(alias);
    }

    @Override
    public List<Album> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Override
    public List<Album> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Album> getMyAlbums(UUID userId, Pageable pageable) {
        return repository.findAllByCreatedBy(userId, pageable);
    }

    @Override
    public Page<Album> searchAlbum(String title, Pageable pageable) {
        return repository.findAllByTitleContaining(title, pageable);
    }

    @Override
    public Page<Album> searchMyAlbums(String title, List<AlbumStatus> status, UUID userId, Pageable pageable) {
        return repository.findAllByTitleContainingAndStatusInAndCreatedBy(title, status, userId, pageable);
    }

    @Override
    public Page<Album> getMyAlbums(List<AlbumStatus> status, UUID userId, Pageable pageable) {
        return repository.findAllByStatusInAndCreatedBy(status, userId, pageable);
    }
}
