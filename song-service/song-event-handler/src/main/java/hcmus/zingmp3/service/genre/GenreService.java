package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.common.domain.model.Genre;

public interface GenreService {
    Genre create(Genre genre);
    void update(Genre genre);
    void delete(Genre genre);
}
