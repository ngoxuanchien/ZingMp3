package hcmus.zingmp3.web.model.dto.mapper;

import hcmus.zingmp3.domain.model.Album;
import hcmus.zingmp3.web.model.dto.AlbumDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AlbumDtoMapper {
    public AlbumDto toDto(Album entity) {
        return new AlbumDto(
                entity.getId(),
                entity.getAlias(),
                entity.getTitle(),
                entity.getThumbnailId(),
                entity.getType(),
                entity.getDescription(),
                entity.getReleaseDate());

    }

    public Set<AlbumDto> toDto(Set<Album> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toSet());
    }
}
