package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.domain.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreCommandService commandService;

    @Override
    public Genre create(Genre object) {
        return commandService.create(object);
    }

    @Override
    public Genre update(Genre object) {
        return commandService.update(object);
    }

    @Override
    public void delete(Genre object) {
        commandService.delete(object);
    }
}
