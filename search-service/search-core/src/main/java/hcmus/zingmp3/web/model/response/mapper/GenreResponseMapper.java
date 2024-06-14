package hcmus.zingmp3.web.model.response.mapper;

import hcmus.zingmp3.domain.model.Genre;
import hcmus.zingmp3.web.model.response.GenreResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreResponseMapper {

    public GenreResponse toResponse(Genre entity) {
        return new GenreResponse(entity.getId(), entity.getAlias(), entity.getName());
    }

    public List<GenreResponse> toResponse(List<Genre> entities) {
        return entities.stream().map(this::toResponse).toList();
    }
}
