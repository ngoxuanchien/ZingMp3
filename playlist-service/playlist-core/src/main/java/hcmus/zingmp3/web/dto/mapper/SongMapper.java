package hcmus.zingmp3.web.dto.mapper;

import hcmus.zingmp3.SongResponseGrpc;
import hcmus.zingmp3.service.artist.ArtistService;
import hcmus.zingmp3.service.genre.GenreService;
import hcmus.zingmp3.service.image.ImageService;
import hcmus.zingmp3.web.dto.SongResponse;
import hcmus.zingmp3.web.dto.SongStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SongMapper {

    private final ArtistMapper artistMapper;
    private final GenreMapper genreMapper;

    public SongResponse toDto(SongResponseGrpc entity) {
        return new SongResponse(
                UUID.fromString(entity.getId()),
                entity.getAlias(),
                entity.getTitle(),
                entity.getThumbnail(),
                artistMapper.toDto(entity.getArtistsList()),
                genreMapper.toDto(entity.getGenresList()),
                artistMapper.toDto(entity.getComposersList()),
                null,
                entity.getReleaseDate(),
                entity.getListen(),
                entity.getLiked(),
                entity.getLyric(),
                null
        );
    }
}
