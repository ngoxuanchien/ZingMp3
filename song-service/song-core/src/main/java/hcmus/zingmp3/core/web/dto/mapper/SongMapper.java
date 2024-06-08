package hcmus.zingmp3.core.web.dto.mapper;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.core.service.genre.GenreService;
import hcmus.zingmp3.core.web.dto.SongRequest;
import hcmus.zingmp3.core.web.dto.SongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SongMapper {
    private final GenreMapper genreMapper;
    private final GenreService genreService;

    public Song toEntity(SongRequest request) {
        var builder = Song.builder()
                .id(request.id())
                .alias(request.alias())
                .title(request.title())
                .lyric(request.lyric());

        builder.artistIds(
                Optional.ofNullable(
                        request.artistIds())
                              .orElse(Set.of()));

        builder.composerIds(
                Optional.ofNullable(
                        request.composerIds())
                                .orElse(Set.of()));

        builder.mediaIds(
                Optional.ofNullable(
                        request.mediaIds())
                              .orElse(Set.of()));

        builder.thumbnailId(
                Optional.ofNullable(
                        request.thumbnailId())
                                    .orElse(UUID.fromString("00000000-0000-0000-0000-000000000000")));

        builder.genreIds(
                Optional.ofNullable(
                        request.genreIds())
                               .orElse(Set.of()));

        return builder.build();
    }

    public SongResponse toDto(Song song) {
        return new SongResponse(
                song.getId(),
                song.getAlias(),
                song.getTitle(),
                song.getThumbnailId().toString(),
                song.getArtistIds(),
                genreService.getAllGenres(song.getGenreIds()),
                song.getComposerIds(),
                song.getStatus(),
                song.getReleaseDate(),
                song.getListen(),
                song.getLiked(),
                song.getLyric(),
                song.getMediaIds()
        );
    }

    public List<SongResponse> toDto(List<Song> songs) {
        return songs.stream()
                .map(this::toDto)
                .toList();
    }
}
