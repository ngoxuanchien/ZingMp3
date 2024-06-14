package hcmus.zingmp3.core.web.dto.mapper;

import hcmus.zingmp3.artist.ArtistResponseGrpc;
import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.core.service.artist.ArtistService;
import hcmus.zingmp3.core.service.genre.GenreService;
import hcmus.zingmp3.core.service.image.ImageService;
import hcmus.zingmp3.core.web.dto.ArtistResponse;
import hcmus.zingmp3.core.web.dto.GenreResponse;
import hcmus.zingmp3.core.web.dto.SongRequest;
import hcmus.zingmp3.core.web.dto.SongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SongMapper {

    private final ImageService imageService;
    private final ArtistService artistService;
    private final GenreService genreService;

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
        Set<ArtistResponse> artists = artistService.getAllById(song.getArtistIds());
        Set<ArtistResponse> composers = artistService.getAllById(song.getComposerIds());
        Set<GenreResponse> genres = genreService.getAllGenres(song.getGenreIds());

        return new SongResponse(
                song.getId(),
                song.getAlias(),
                song.getTitle(),
                imageService.getById(song.getThumbnailId()).getUrl(),
                artists,
                genres,
                composers,
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
