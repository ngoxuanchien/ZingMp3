package hcmus.zingmp3.genre;

import hcmus.zingmp3.exception.GenreNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public void createGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void updateGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(UUID id) {
        genreRepository.deleteById(id);
    }

    @Override
    public List<GenreRestResponse> getAllGenres(Pageable pageable) {
        return genreRepository.findAll(pageable)
                .stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    @Override
    public GenreRestResponse getGenreById(UUID genreId) {
        return genreRepository.findById(genreId)
                .map(genreMapper::toDTO)
                .orElseThrow(() -> new GenreNotFoundException(
                        String.format("No genre found with the provided id: %s", genreId.toString())
                ));
    }

    @Override
    public List<GenreRestResponse> getAllGenres() {
        return StreamSupport.stream(genreRepository.findAll().spliterator(), true)
                .map(genreMapper::toDTO)
                .toList();
    }
}
