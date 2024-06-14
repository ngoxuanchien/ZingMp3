package hcmus.zingmp3.web.model.response.mapper;

import hcmus.zingmp3.domain.model.Album;
import hcmus.zingmp3.web.model.dto.mapper.ArtistDtoMapper;
import hcmus.zingmp3.web.model.dto.mapper.SongDtoMapper;
import hcmus.zingmp3.web.model.response.AlbumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlbumResponseMapper {

    private final SongDtoMapper songMapper;
    private final ArtistDtoMapper artistMapper;

    public AlbumResponse toResponse(Album entity) {
        return new AlbumResponse(
                entity.getId(),
                entity.getAlias(),
                entity.getTitle(),
                entity.getThumbnailId(),
                entity.getType(),
                entity.getDescription(),
                artistMapper.toDto(entity.getArtists()),
                entity.getReleaseDate(),
                songMapper.toDto(entity.getSongs())
        );

    }

    public List<AlbumResponse> toResponse(List<Album> entities) {
        return entities.stream().map(this::toResponse).toList();
    }
}
