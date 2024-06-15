package hcmus.zingmp3.web.dto.mapper;

import hcmus.zingmp3.GenreGrpcResponse;
import hcmus.zingmp3.common.domain.model.Genre;
import hcmus.zingmp3.web.dto.GenreRequest;
import hcmus.zingmp3.web.dto.GenreResponse;
import org.springframework.data.domain.Page;
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

    public GenreGrpcResponse toGrpc(Genre genre) {
        return GenreGrpcResponse.newBuilder()
                .setId(genre.getId().toString())
                .setName(genre.getName())
                .setAlias(genre.getAlias())
                .build();
    }

    public List<GenreGrpcResponse> toGrpc(Set<Genre> genres) {
        return genres.stream()
                .map(this::toGrpc)
                .toList();
    }

    public Page<GenreResponse> toDto(Page<Genre> pages) {
        return pages.map(this::toDto);
    }
}
