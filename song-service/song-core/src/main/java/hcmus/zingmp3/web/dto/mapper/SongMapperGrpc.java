package hcmus.zingmp3.web.dto.mapper;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.SongResponseGrpc;
import hcmus.zingmp3.SongStatusGrpc;
import hcmus.zingmp3.service.artist.ArtistService;
import hcmus.zingmp3.service.genre.GenreService;
import hcmus.zingmp3.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SongMapperGrpc {

    private final GenreService genreService;

    private final ImageService imageService;

    private final ArtistService artistService;

    public SongResponseGrpc toDto(Song entity) {
        return SongResponseGrpc.newBuilder()
                .setId(entity.getId().toString())
                .setAlias(entity.getAlias())
                .setTitle(entity.getTitle())
                .setThumbnail(imageService.getById(entity.getThumbnailId()).getUrl())
                .addAllArtists(artistService.getAllByIdGrpc(entity.getArtistIds()))
                .addAllGenres(genreService.getAllById(entity.getGenreIds()))
                .addAllComposers(artistService.getAllByIdGrpc(entity.getComposerIds()))
                .setStatus(SongStatusGrpc.valueOf(entity.getStatus().name()))
                .setReleaseDate(entity.getReleaseDate())
                .setListen(entity.getListen())
                .setLiked(entity.getLiked())
                .setLyric(Objects.requireNonNullElse(entity.getLyric(), ""))
                .addAllMediaIds(entity.getMediaIds().stream().map(UUID::toString).collect(Collectors.toList()))
                .setCreatedBy(entity.getCreatedBy().toString())
                .setCreatedDate(String.valueOf(entity.getCreatedDate()))
                .setLastModifiedBy(Objects.requireNonNullElse(entity.getLastModifiedBy(), "").toString())
                .setLastModifiedDate(Objects.requireNonNullElse(entity.getLastModifiedDate(), "").toString())
                .setDuration(entity.getDuration())
                .build();
    }
}
