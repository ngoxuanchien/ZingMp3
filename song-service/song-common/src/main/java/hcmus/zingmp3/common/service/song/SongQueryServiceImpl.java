package hcmus.zingmp3.common.service.song;

import hcmus.zingmp3.common.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.domain.model.SongStatus;
import hcmus.zingmp3.common.repository.SongRepository;
import hcmus.zingmp3.common.service.song.SongQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SongQueryServiceImpl implements SongQueryService {
    private final SongRepository repository;

    @Override
    public Song getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Song with id %s not found", id)
                ));
    }

    @Override
    public Song getByAlias(String alias) {
        return repository.findByAlias(alias)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Song with alias %s not found", alias)
                ));
    }

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByAlias(String alias) {
        return repository.existsByAlias(alias);
    }

    @Override
    public Page<Song> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Song> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Song> getAllByCreatedBy(UUID userId, Pageable pageable) {
        return repository.findAllByCreatedBy(userId, pageable);
    }

    @Override
    public Page<Song> searchSong(String title, Pageable pageable) {
        return repository.findAllByTitleContaining(title, pageable);
    }

    @Override
    public Page<Song> searchMySongs(String name, List<SongStatus> status, UUID userId, Pageable pageable) {
        return repository.findAllByTitleLikeAndStatusInAndCreatedBy(name, status , userId, pageable);
    }

    @Override
    public Page<Song> getAllMySongs(List<SongStatus> status, UUID userId, Pageable pageable) {
        return repository.findAllByStatusInAndCreatedBy(status, userId, pageable);
    }
}
