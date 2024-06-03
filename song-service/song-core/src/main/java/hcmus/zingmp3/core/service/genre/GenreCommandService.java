package hcmus.zingmp3.core.service.genre;

import hcmus.zingmp3.common.domain.model.Genre;
import hcmus.zingmp3.core.service.CommandService;

public interface GenreCommandService extends CommandService<Genre> {
    void update(Genre object);
    void delete(Genre object);
}
