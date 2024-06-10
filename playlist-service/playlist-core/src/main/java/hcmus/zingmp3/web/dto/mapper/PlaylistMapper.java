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
        var playlist = Playlist.builder()
                .alias(dto.alias())
                .title(dto.title())
                .description(dto.description())
                .isPublic(dto.isPublic())
                .build();

        playlist.setId(dto.id());
        playlist.setThumbnailId(dto.thumbnailId());
        playlist.setArtistIds(dto.artistIds());
        playlist.setSongIds(dto.songIds());
        playlist.setType(dto.type());

        return playlist;
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
                entity.getCreatedDate(),
                entity.getLastModifiedBy(),
                entity.getLastModifiedDate()
        );
    }

    public List<PlaylistResponse> toDto(List<Playlist> playlists) {
        return playlists.stream().map(this::toDto).toList();
    }
}
