package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.common.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository repository;
    @Override
    public void create(
            final Artist artist
    ) {
        repository.save(artist);
    }

    @Override
    public void update(Artist artist) {
        repository.save(artist);
    }

    @Override
    public void delete(Artist artist) {
        repository.delete(artist);
    }
}
