package hcmus.zingmp3.web.dto.mapper;

import hcmus.zingmp3.common.domain.model.Playlist;
import hcmus.zingmp3.service.artist.ArtistService;
import hcmus.zingmp3.service.image.ImageService;
import hcmus.zingmp3.service.song.SongService;
import hcmus.zingmp3.web.dto.PlaylistRequest;
import hcmus.zingmp3.web.dto.PlaylistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlaylistMapper {

    private final ImageService imageService;
    private final ArtistService artistService;
    private final SongService songService;

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
                imageService.getById(entity.getThumbnailId()).getUrl(),
                entity.getPlaylistType(),
                entity.getDescription(),
                artistService.getAllById(entity.getArtistIds()),
                songService.getAllById(entity.getSongIds()),
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
