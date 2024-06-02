package hcmus.zingmp3.genreevent;

import hcmus.zingmp3.genre.Genre;
import org.springframework.stereotype.Service;

@Service
public interface GenreEventService {
    void createGenre(Genre genre);
    void updateGenre(Genre genre);
    void deleteGenre(Genre genre);
}
