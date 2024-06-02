package hcmus.zingmp3.song;

import hcmus.zingmp3.exception.SongNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongRepository repository;
    private final SongMapper mapper;

    @Override
    public List<SongRestResponse> getAllSongs() {
        return mapper.toDTOs(repository.findAll());
    }

    @Override
    public SongRestResponse getSongById(UUID songId) {

        return mapper.toDTO(
                repository.findById(songId)
                        .orElseThrow(() -> new SongNotFoundException(
                                String.format("No song found with the provided Id %s", songId)))
        );
    }

    @Override
    public List<SongRestResponse> getAllSongs(Pageable pageable) {
        return List.of();
    }

    @Override
    public void createSong(Song song) {
        repository.save(song);
    }

    @Override
    public void updateSong(Song song) {
        repository.save(song);
    }

    @Override
    public void deleteSong(UUID id) {
        repository.deleteById(id);
    }
}
