package hcmus.zingmp3.genre;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface GenreService {
    GenreRestResponse createGenre(GenreRestRequest request);
    GenreRestResponse updateGenre(UUID id, GenreRestRequest request);
    void deleteGenre(UUID id);
}
