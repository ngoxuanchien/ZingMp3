package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.dto.genre.GenreRequest;
import hcmus.zingmp3.dto.genre.GenreResponse;

import java.util.UUID;

public interface GenreService {

    GenreResponse createGenre(GenreRequest genreRequest);
    void deleteGenre(UUID genreId);

    boolean isGenreExist(String genreAlias);

    GenreResponse getOrCreateIfNotExist(GenreRequest genreRequest);

    GenreResponse getGenreByAlias(String genreAlias);
}
