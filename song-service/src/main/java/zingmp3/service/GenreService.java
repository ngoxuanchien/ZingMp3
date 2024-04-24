package zingmp3.service;


import zingmp3.dto.GenreDto;

import java.util.List;
import java.util.UUID;

public interface GenreService {
    GenreDto findById(UUID id);
    List<GenreDto> findAll();
    GenreDto create(GenreDto genreDto);
    GenreDto update(UUID id, GenreDto genreDto);
    void delete(UUID id);
}
