package hcmus.zingmp3.genre;

import hcmus.zingmp3.genre.model.Genre;
import hcmus.zingmp3.genre.model.GenreRequest;
import hcmus.zingmp3.genre.model.GenreResponse;
import hcmus.zingmp3.genre.model.Genre;
import hcmus.zingmp3.genre.model.GenreRequest;
import hcmus.zingmp3.genre.model.GenreResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreMapper {
    public GenreResponse toDTO(Genre entity) {
        GenreResponse dto = new GenreResponse();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public Genre toEntity(GenreRequest dto) {
        Genre entity = new Genre();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public List<GenreResponse> toDTO(List<Genre> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
