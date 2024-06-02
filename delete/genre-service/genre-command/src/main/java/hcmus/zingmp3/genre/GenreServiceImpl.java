package hcmus.zingmp3.genre;

import hcmus.zingmp3.exception.GenreNotFoundException;
import hcmus.zingmp3.genreevent.GenreEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreMapper genreMapper;
    private final GenreRepository genreRepository;
    private final GenreEventService eventService;

    @Override
    public GenreRestResponse createGenre(GenreRestRequest request) {
        Genre newGenre = genreRepository.save(genreMapper.toEntity(request));
        eventService.createGenre(newGenre);
        return genreMapper.toDTO(newGenre);
    }

    @Override
    public GenreRestResponse updateGenre(UUID id, GenreRestRequest request) {
        var genre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(
                        String.format("Cannot update: Genre not found with the provided id: %s", id)
                ));
        mergeGenre(genre, request);
        var newGenre = genreRepository.save(genre);

        eventService.updateGenre(newGenre);
        return genreMapper.toDTO(newGenre);
    }

    private void mergeGenre(Genre genre, GenreRestRequest request) {
        if (!request.getName().isBlank()) {
            genre.setName(request.getName());
        }

        if (!request.getTitle().isBlank()) {
            genre.setTitle(request.getTitle());
        }

        if (!request.getSongs().isEmpty()) {
            genre.setSongs(request.getSongs());
        }
    }

    @Override
    public void deleteGenre(UUID id) {
        var genre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(
                        String.format("Cannot delete: Genre not found with the provided id: %s", id)
                ));
        eventService.deleteGenre(genre);
        genreRepository.delete(genre);
    }
}
