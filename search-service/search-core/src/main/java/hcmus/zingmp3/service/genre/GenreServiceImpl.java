package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.web.model.response.GenreResponse;
import hcmus.zingmp3.web.model.response.mapper.GenreResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreQueryService queryService;
    private final GenreResponseMapper mapper;

    @Override
    public GenreResponse getGenreById(UUID id) {
        return mapper.toResponse(queryService.getById(id));
    }

    @Override
    public List<GenreResponse> getAllGenres() {
        return mapper.toResponse(queryService.getAll());
    }

    @Override
    public List<GenreResponse> getAllGenresByName(String name) {
        return mapper.toResponse(queryService.getAllByNameContaining(name));
    }
}
