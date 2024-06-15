package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.GenreGrpcResponse;
import hcmus.zingmp3.common.domain.model.Genre;
import hcmus.zingmp3.common.service.QueryService;
import hcmus.zingmp3.service.CommandService;
import hcmus.zingmp3.web.dto.GenreRequest;
import hcmus.zingmp3.web.dto.GenreResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface GenreService extends CommandService<Genre>, QueryService<Genre> {
    GenreResponse createGenre(GenreRequest request);

    GenreResponse getGenreById(UUID genreId);

    GenreResponse getGenreByAlias(String alias);

    List<GenreResponse> getAllGenres();

    Page<GenreResponse> getAllGenres(Pageable pageable);

    void deleteGenre(UUID genreId);

    GenreResponse updateGenre(GenreRequest request);

    List<GenreResponse> getAllGenres(List<UUID> genreIds);

    List<GenreGrpcResponse> getAllById(List<UUID> genreIds);
}
