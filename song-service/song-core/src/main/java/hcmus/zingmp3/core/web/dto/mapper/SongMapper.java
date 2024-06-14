package hcmus.zingmp3.core.web.dto.mapper;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.core.service.genre.GenreService;
import hcmus.zingmp3.core.service.image.ImageService;
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

    private final ImageService imageService;

    public Song toEntity(SongRequest request) {
        var song = Song.builder()
                .alias(request.alias())
                .title(request.title())
                .lyric(request.lyric())
                .build();

        song.setId(request.id());
        song.setReleaseDate(request.releaseDate());
        song.setThumbnailId(request.thumbnailId());
        song.setArtistIds(request.artistIds());
        song.setComposerIds(request.composerIds());
        song.setGenreIds(request.genreIds());
        song.setLiked(request.liked());
        song.setListen(request.listen());
        song.setMediaIds(request.mediaIds());
        return song;
    }

    public SongResponse toDto(Song song) {
        return new SongResponse(
                song.getId(),
                song.getAlias(),
                song.getTitle(),
                imageService.getById(song.getThumbnailId()).getUrl(),
                song.getArtistIds(),
                song.getGenreIds(),
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
