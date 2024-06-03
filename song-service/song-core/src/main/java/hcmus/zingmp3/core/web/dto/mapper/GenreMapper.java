package hcmus.zingmp3.core.web.dto.mapper;

import hcmus.zingmp3.common.domain.model.Genre;
import hcmus.zingmp3.core.web.dto.GenreRequest;
import hcmus.zingmp3.core.web.dto.GenreResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GenreMapper {
    public Genre toEntity(GenreRequest request) {
        return Genre.builder()
                .name(request.name())
                .alias(request.alias())
                .build();
    }

    public GenreResponse toDto(Genre genre) {
        return new GenreResponse(
                genre.getId(),
                genre.getName(),
                genre.getAlias()
        );
    }

    public List<GenreResponse> toDto(List<Genre> genres) {
        return genres.stream()
                .map(this::toDto)
                .toList();
    }

    public Set<GenreResponse> toDto(Set<Genre> genres) {
        return genres.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }
}
