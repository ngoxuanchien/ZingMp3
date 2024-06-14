package hcmus.zingmp3.web.model.response.mapper;

import hcmus.zingmp3.domain.model.Artist;
import hcmus.zingmp3.web.model.dto.mapper.SongDtoMapper;
import hcmus.zingmp3.web.model.response.ArtistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArtistResponseMapper {

    private final SongDtoMapper songMapper;

    public ArtistResponse toResponse(Artist entity) {
        return new ArtistResponse(
                entity.getId(),
                entity.getName(),
                entity.getAlias(),
                entity.getThumbnailId(),
                entity.getRealName(),
                songMapper.toDto(entity.getSongs()),
                songMapper.toDto(entity.getComposedSongs())
        );
    }

    public List<ArtistResponse> toResponse(List<Artist> entities) {
        return entities.stream().map(this::toResponse).toList();
    }


}
