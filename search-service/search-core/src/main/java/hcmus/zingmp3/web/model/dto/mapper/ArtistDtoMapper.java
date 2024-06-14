package hcmus.zingmp3.web.model.dto.mapper;

import hcmus.zingmp3.domain.model.Artist;
import hcmus.zingmp3.web.model.dto.ArtistDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ArtistDtoMapper {
    public ArtistDto toDto(Artist entity) {
        return new ArtistDto(
                entity.getId(),
                entity.getName(),
                entity.getAlias(),
                entity.getThumbnailId(),
                entity.getRealName()
        );
    }

    public Set<ArtistDto> toDto(Set<Artist> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toSet());
    }
}
