package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.GenreGrpcResponse;
import hcmus.zingmp3.common.domain.exception.ResourceAlreadyExistsException;
import hcmus.zingmp3.common.domain.model.Genre;
import hcmus.zingmp3.common.service.genre.GenreQueryService;
import hcmus.zingmp3.web.dto.GenreRequest;
import hcmus.zingmp3.web.dto.GenreResponse;
import hcmus.zingmp3.service.genre.GenreService;
import hcmus.zingmp3.web.dto.mapper.GenreMapper;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreQueryService queryService;
    private final GenreCommandService commandService;
    private final GenreMapper mapper;

    @Override
    public Genre getById(UUID id) {
        return queryService.getById(id);
    }

    @Override
    public Genre getByAlias(String alias) {
        return queryService.getByAlias(alias);
    }

    @Override
    public boolean existsById(UUID id) {
        return queryService.existsById(id);
    }

    @Override
    public boolean existsByAlias(String alias) {
        return queryService.existsByAlias(alias);
    }

    @Override
    public Page<Genre> getAll(Pageable pageable) {
        return queryService.getAll(pageable);
    }

    @Override
    public List<Genre> getAll() {
        return queryService.getAll();
    }

    @Override
    public void update(Genre object) {
        commandService.update(object);
    }

    @Override
    public void delete(Genre object) {
        commandService.delete(object);
    }

    @Override
    public void create(Genre object) {
        commandService.create(object);
    }

    @Override
    public GenreResponse createGenre(GenreRequest request) {
        if (queryService.existsByAlias(request.alias())) {
            throw new ResourceAlreadyExistsException(
                    String.format("Genre with alias %s already exists", request.alias())
            );
        }

        var genre = mapper.toEntity(request);
        genre.setId(UUID.randomUUID());
        create(genre);
        return mapper.toDto(genre);
    }

    @Override
    public GenreResponse getGenreById(UUID genreId) {
        return mapper.toDto(getById(genreId));
    }

    @Override
    public GenreResponse getGenreByAlias(String alias) {
        return mapper.toDto(getByAlias(alias));
    }

    @Override
    public List<GenreResponse> getAllGenres() {
        return mapper.toDto(getAll());
    }

    @Override
    public Page<GenreResponse> getAllGenres(Pageable pageable) {
        return mapper.toDto(getAll(pageable));
    }

    @Override
    public void deleteGenre(UUID genreId) {
        var genre = getById(genreId);
        delete(genre);
    }

    @Override
    public GenreResponse updateGenre(GenreRequest request) {
        var genre = getByAlias(request.alias());
        merge(genre, request);
        update(genre);
        return mapper.toDto(genre);
    }

    @Override
    public List<GenreResponse> getAllGenres(List<UUID> genreIds) {
        if (genreIds == null) {
            return List.of();
        }

        return genreIds.stream()
                .map(this::getById)
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<GenreGrpcResponse> getAllById(List<UUID> genreIds) {
        return genreIds.stream()
                .map(this::getById)
                .map(mapper::toGrpc)
                .toList();
    }

    private void merge(Genre genre, GenreRequest request) {
        if (StringUtils.isNotBlank(request.name())) {
            genre.setName(request.name());
        }
    }
}
