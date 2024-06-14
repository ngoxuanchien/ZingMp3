package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.domain.model.Artist;
import hcmus.zingmp3.repository.elasticsearch.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistCommandServiceImpl implements ArtistCommandService {

    private final ArtistRepository repository;

    @Override
    public Artist create(Artist object) {
        return repository.save(object);
    }

    @Override
    public Artist update(Artist object) {
        return repository.save(object);
    }

    @Override
    public void delete(Artist object) {
        repository.delete(object);
    }
}
