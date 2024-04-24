package zingmp3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zingmp3.converter.model.GenreConverter;
import zingmp3.dto.GenreDto;
import zingmp3.exception.GenreNotFoundException;
import zingmp3.model.Genre;
import zingmp3.repository.GenreRepository;
import zingmp3.service.GenreService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreConverter genreConverter;

    @Override
    public GenreDto findById(UUID id) {
        return genreRepository.findById(id)
                .map(genreConverter::convert)
                .orElseThrow(() -> new GenreNotFoundException("Genre not found"));
    }

    @Override
    public List<GenreDto> findAll() {
        return genreRepository.findAll()
                .stream()
                .map(genreConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDto create(GenreDto genreDto) {
        Genre genre = genreConverter.reverseConvert(genreDto);
        genre.setId(null);
        return genreConverter.convert(genreRepository.save(genre));
    }

    @Override
    public GenreDto update(UUID id, GenreDto genreDto) {
        if (!genreRepository.existsById(id)) {
            throw new GenreNotFoundException("Genre not found");
        }
        Genre genre = genreConverter.reverseConvert(genreDto);
        genre.setId(id);
        return genreConverter.convert(genreRepository.save(genre));
    }

    @Override
    public void delete(UUID id) {
        if (!genreRepository.existsById(id)) {
            throw new GenreNotFoundException("Genre not found");
        }
        genreRepository.deleteById(id);
    }
}
