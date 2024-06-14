package hcmus.zingmp3.service.song;

import hcmus.zingmp3.domain.model.Song;
import hcmus.zingmp3.repository.elasticsearch.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SongQueryServiceImpl implements SongQueryService {

    private final SongRepository songRepository;

    @Override
    public Song getById(UUID id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    public List<Song> getAll() {
        return StreamSupport
                .stream(songRepository.findAll().spliterator(), false)
                .toList();
    }

    @Override
    public List<Song> getAllByTitle(String title) {
        return songRepository.findByTitleContaining(title);
    }
}
