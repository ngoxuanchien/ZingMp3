package hcmus.zingmp3.genre;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface GenreService {
    void createGenre(Genre genre);
    void updateGenre(Genre genre);
    void deleteGenre(UUID id);


    List<GenreRestResponse> getAllGenres(Pageable pageable);

    GenreRestResponse getGenreById(UUID genreId);

    List<GenreRestResponse> getAllGenres();
}
