package hcmus.zingmp3.genre;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreMapper {
    public GenreRestResponse toDTO(Genre entity) {
        GenreRestResponse dto = new GenreRestResponse();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public Genre toEntity(GenreRestRequest dto) {
        Genre entity = new Genre();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public List<GenreRestResponse> toDTO(List<Genre> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
