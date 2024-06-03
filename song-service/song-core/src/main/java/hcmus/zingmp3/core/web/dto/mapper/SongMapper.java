package hcmus.zingmp3.core.web.dto.mapper;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.core.web.dto.SongRequest;
import hcmus.zingmp3.core.web.dto.SongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SongMapper {
    private final GenreMapper genreMapper;

    public Song toEntity(SongRequest request) {
        return Song.builder()
                .alias(request.alias())
                .title(request.title())
                .lyric(request.lyric())
                .build();
    }

    public SongResponse toDto(Song song) {
        return new SongResponse(
                song.getId(),
                song.getAlias(),
                song.getTitle(),
                song.getThumbnailId().toString(),
                song.getArtistIds(),
                genreMapper.toDto(song.getGenres()),
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
