package hcmus.zingmp3.web.dto.mapper;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.service.artist.ArtistService;
import hcmus.zingmp3.service.genre.GenreService;
import hcmus.zingmp3.service.image.ImageService;
import hcmus.zingmp3.service.media.MediaService;
import hcmus.zingmp3.web.dto.ArtistResponse;
import hcmus.zingmp3.web.dto.GenreResponse;
import hcmus.zingmp3.web.dto.SongRequest;
import hcmus.zingmp3.web.dto.SongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SongMapper {

    private final ImageService imageService;
    private final ArtistService artistService;
    private final GenreService genreService;
    private final MediaService mediaService;

    public Song toEntity(SongRequest request) {
        var song = Song.builder()
                .alias(request.alias())
                .title(request.title())
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
        song.setLyric(request.lyric());
        return song;
    }

    public SongResponse toDto(Song song) {
        List<ArtistResponse> artists = artistService.getAllById(song.getArtistIds());
        List<ArtistResponse> composers = artistService.getAllById(song.getComposerIds());
        List<GenreResponse> genres = genreService.getAllGenres(song.getGenreIds());

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
                song.getMediaIds(),
                song.getDuration()
        );
    }

    public List<SongResponse> toDto(List<Song> songs) {
        return songs.stream()
                .map(this::toDto)
                .toList();
    }

    public Page<SongResponse> toDto(Page<Song> pages) {
        return pages.map(this::toDto);
    }
}
