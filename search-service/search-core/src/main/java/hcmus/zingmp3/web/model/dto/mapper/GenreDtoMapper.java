package hcmus.zingmp3.web.model.dto.mapper;

import hcmus.zingmp3.domain.model.Genre;
import hcmus.zingmp3.web.model.dto.GenreDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GenreDtoMapper {
    public GenreDto toDto(Genre entity) {
        return new GenreDto(entity.getId(), entity.getAlias(), entity.getName());
    }

    public Set<GenreDto> toDto(Set<Genre> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toSet());
    }
}
