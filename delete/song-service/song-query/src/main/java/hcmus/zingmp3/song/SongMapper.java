package hcmus.zingmp3.song;

import hcmus.zingmp3.artist.ArtistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class SongMapper {
    private final ArtistMapper artistMapper;

    public SongRestResponse toDTO(Song song) {
        return SongRestResponse.builder()
                .id(song.getId())
                .title(song.getTitle())
                .alias(song.getAlias())
                .isOfficial(song.isOfficial())
                .isWorldWide(song.isWorldWide())
                .thumbnail(song.getThumbnail())
                .isPrivate(song.isPrivate())
                .releaseDate(song.getReleaseDate())
                .distributor(song.getDistributor())
                .hasLyric(song.isHasLyric())
                .likes(song.getLikes())
                .listen(song.getListen())
                .comment(song.getComment())
                .artists(artistMapper.toDTOs(song.getArtists()))
                .composers(artistMapper.toDTOs(song.getComposers()))
                .genres(song.getGenres())
                .audios(song.getAudios())
                .build();
    }

    public List<SongRestResponse> toDTOs(Iterable<Song> songs) {
        return StreamSupport.stream(songs.spliterator(), true)
                .map(this::toDTO)
                .toList();
    }
}
