package hcmus.zingmp3.web.dto.mapper;

import hcmus.zingmp3.common.domain.model.Playlist;
import hcmus.zingmp3.web.dto.PlaylistRequest;
import hcmus.zingmp3.web.dto.PlaylistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlaylistMapper {

    public Playlist toEntity(PlaylistRequest dto) {
        return Playlist.builder()
                .id(dto.id())
                .alias(dto.alias())
                .title(dto.title())
                .thumbnailId(dto.thumbnailId())
                .type(dto.type())
                .description(dto.description())
                .artistIds(dto.artistIds())
                .songIds(dto.songIds())
                .isPublic(dto.isPublic())
                .build();
    }

    public PlaylistResponse toDto(Playlist entity) {
        return new PlaylistResponse(
                entity.getId(),
                entity.getAlias(),
                entity.getTitle(),
                entity.getThumbnailId(),
                entity.getType(),
                entity.getDescription(),
                entity.getArtistIds(),
                entity.getSongIds(),
                entity.isPublic(),
                entity.getCreatedBy(),
                entity.getCreateDate()
        );
    }

    public List<PlaylistResponse> toDto(List<Playlist> playlists) {
        return playlists.stream().map(this::toDto).toList();
    }
}
