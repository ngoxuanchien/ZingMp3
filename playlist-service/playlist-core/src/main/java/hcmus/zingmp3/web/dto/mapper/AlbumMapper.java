package hcmus.zingmp3.web.dto.mapper;

import hcmus.zingmp3.common.domain.model.Album;
import hcmus.zingmp3.web.dto.AlbumRequest;
import hcmus.zingmp3.web.dto.AlbumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlbumMapper {

    public Album toEntity(AlbumRequest dto) {
        return Album.builder()
                .id(dto.id())
                .alias(dto.alias())
                .title(dto.title())
                .type(dto.type())
                .description(dto.description())
                .artistIds(dto.artistIds())
                .releaseDate(dto.releaseDate())
                .songIds(dto.songIds())
                .build();
    }

    public AlbumResponse toDto(Album entity) {
        return new AlbumResponse(
                entity.getId(),
                entity.getAlias(),
                entity.getTitle(),
                entity.getType(),
                entity.getDescription(),
                entity.getArtistIds(),
                entity.getReleaseDate(),
                entity.getStatus(),
                entity.getSongIds(),
                entity.getCreateDate(),
                entity.getDistributorId()
        );
    }

    public List<AlbumResponse> toDto(List<Album> albums) {
        return albums.stream()
                .map(this::toDto)
                .toList();
    }
}
