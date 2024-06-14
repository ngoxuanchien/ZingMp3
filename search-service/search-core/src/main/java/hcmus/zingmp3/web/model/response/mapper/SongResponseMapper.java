package hcmus.zingmp3.web.model.response.mapper;

import hcmus.zingmp3.domain.model.Song;
import hcmus.zingmp3.web.model.dto.mapper.AlbumDtoMapper;
import hcmus.zingmp3.web.model.dto.mapper.ArtistDtoMapper;
import hcmus.zingmp3.web.model.dto.mapper.GenreDtoMapper;
import hcmus.zingmp3.web.model.dto.mapper.MediaDtoMapper;
import hcmus.zingmp3.web.model.response.SongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SongResponseMapper {

    private final AlbumDtoMapper albumMapper;
    private final ArtistDtoMapper artistMapper;
    private final GenreDtoMapper genreMapper;
    private final MediaDtoMapper mediaMapper;

    public SongResponse toResponse(Song entity) {
        return new SongResponse(
                entity.getId(),
                entity.getAlias(),
                entity.getTitle(),
                entity.getThumbnailId(),
                artistMapper.toDto(entity.getArtists()),
                genreMapper.toDto(entity.getGenres()),
                artistMapper.toDto(entity.getComposers()),
                entity.getReleaseDate(),
                entity.getListen(),
                entity.getLiked(),
                entity.getLyric(),
                mediaMapper.toDto(entity.getMedias()),
                entity.getDuration(),
                albumMapper.toDto(entity.getAlbum())

        );
    }

    public List<SongResponse> toResponse(List<Song> entities) {
        return entities.stream().map(this::toResponse).toList();
    }


}
