package hcmus.zingmp3.genre;

import hcmus.zingmp3.exception.NotFoundException;
import hcmus.zingmp3.genre.model.Genre;
import hcmus.zingmp3.genre.model.GenreRequest;
import hcmus.zingmp3.genre.model.GenreResponse;
import hcmus.zingmp3.exception.NotFoundException;
import hcmus.zingmp3.genre.model.Genre;
import hcmus.zingmp3.genre.model.GenreRequest;
import hcmus.zingmp3.genre.model.GenreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreMapper genreMapper;
    private final GenreRepository genreRepository;

    @Override
    public GenreResponse createGenre(GenreRequest request) {
        Genre newGenre = genreMapper.toEntity(request);
        return genreMapper.toDTO(genreRepository.save(newGenre));
    }

    @Override
    public GenreResponse getGenreById(UUID id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre not found with id: " + id));
        return genreMapper.toDTO(genre);
    }

    @Override
    public List<GenreResponse> getAllGenres(Pageable pageable) {
        return genreMapper.toDTO(genreRepository.findAll(pageable).getContent());
    }

    @Override
    public GenreResponse updateGenre(UUID id, GenreRequest request) {
        genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre not found with id: " + id));
        Genre newGenre = genreMapper.toEntity(request);
        newGenre.setId(id);
        return genreMapper.toDTO(genreRepository.save(newGenre));
    }

    @Override
    public void deleteGenre(UUID id) {
        genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre not found with id: " + id));
        genreRepository.deleteById(id);
    }

    @Override
    public Genre getOrCreateByAlias(String alias) {
        return genreRepository.findByAlias(alias).orElseGet(() -> genreRepository.save(Genre.builder().alias(alias).build()));
    }

}
