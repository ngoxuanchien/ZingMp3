package hcmus.zingmp3.core.service.genre;

import hcmus.zingmp3.common.service.genre.GenreQueryService;
import hcmus.zingmp3.core.web.dto.GenreRequest;
import hcmus.zingmp3.core.web.dto.GenreResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface GenreService extends GenreCommandService, GenreQueryService {
    GenreResponse createGenre(GenreRequest request);

    GenreResponse getGenreById(UUID genreId);

    GenreResponse getGenreByAlias(String alias);

    List<GenreResponse> getAllGenres();

    List<GenreResponse> getAllGenres(Pageable pageable);

    void deleteGenre(UUID genreId);

    GenreResponse updateGenre(GenreRequest request);
}
