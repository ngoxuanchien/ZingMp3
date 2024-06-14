package hcmus.zingmp3.web.dto.mapper;

import hcmus.zingmp3.GenreGrpcResponse;
import hcmus.zingmp3.web.dto.GenreResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GenreMapper {
    public GenreResponse toDto(GenreGrpcResponse response) {
        return new GenreResponse(
                UUID.fromString(response.getId()),
                response.getAlias(),
                response.getName()
        );
    }

    public List<GenreResponse> toDto(List<GenreGrpcResponse> genres) {
        return genres.stream().map(this::toDto).toList();
    }
}
