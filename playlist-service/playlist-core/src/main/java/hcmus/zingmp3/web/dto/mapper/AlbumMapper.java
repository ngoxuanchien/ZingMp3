package hcmus.zingmp3.web.dto.mapper;

import hcmus.zingmp3.common.domain.model.Album;
import hcmus.zingmp3.service.artist.ArtistService;
import hcmus.zingmp3.service.image.ImageService;
import hcmus.zingmp3.service.song.SongService;
import hcmus.zingmp3.web.dto.AlbumRequest;
import hcmus.zingmp3.web.dto.AlbumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlbumMapper {

    private final ImageService imageService;

    private final ArtistService artistService;

    private final SongService songService;

    public Album toEntity(AlbumRequest dto) {
        var album = Album.builder()
                .alias(dto.alias())
                .title(dto.title())
                .albumType(dto.type())
                .description(dto.description())
                .releaseDate(dto.releaseDate())
                .build();

        album.setId(dto.id());
        album.setArtistIds(dto.artistIds());
        album.setSongIds(dto.songIds());
        album.setThumbnailId(dto.thumbnailId());
        return album;
    }

    public AlbumResponse toDto(Album entity) {
        return new AlbumResponse(
                entity.getId(),
                entity.getAlias(),
                entity.getTitle(),
                imageService.getById(entity.getThumbnailId()).getUrl(),
                entity.getAlbumType(),
                entity.getDescription(),
                artistService.getAllById(entity.getArtistIds()),
                entity.getReleaseDate(),
                entity.getStatus(),
                songService.getAllById(entity.getSongIds()),
                entity.getCreatedDate(),
                entity.getCreatedBy(),
                entity.getLastModifiedDate(),
                entity.getLastModifiedBy()
        );
    }

    public List<AlbumResponse> toDto(List<Album> albums) {
        return albums.stream()
                .map(this::toDto)
                .toList();
    }

    public Page<AlbumResponse> toDto(Page<Album> albums) {
        return albums.map(this::toDto);
    }
}
