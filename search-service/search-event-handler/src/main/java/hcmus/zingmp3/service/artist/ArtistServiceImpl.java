package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.domain.model.Artist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistCommandService commandService;

    @Override
    public Artist create(Artist object) {
        return commandService.create(object);
    }

    @Override
    public Artist update(Artist object) {
        return commandService.update(object);
    }

    @Override
    public void delete(Artist object) {
        commandService.delete(object);
    }
}
