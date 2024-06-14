package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.web.model.response.GenreResponse;

import java.util.List;
import java.util.UUID;

public interface GenreService {
    GenreResponse getGenreById(UUID id);

    List<GenreResponse> getAllGenres();

    List<GenreResponse> getAllGenresByName(String name);
}
