package hcmus.zingmp3.song;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class SongMapper {
    private final static UUID DEFAULT_THUMBNAIL_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    public Song toEntity(SongRestRequest request) {
        return Song.builder()
                .alias(request.getAlias())
                .title(request.getTitle())
                .isOfficial(request.isOfficial())
                .isWorldWide(request.isWorldWide())
                .thumbnail(request.getThumbnail())
                .isPrivate(request.isPrivate())
                .releaseDate(request.getReleaseDate())
                .distributor(request.getDistributor())
                .hasLyric(request.isHasLyric())
                .likes(request.getLikes())
                .listen(request.getListen())
                .comment(request.getComment())
                .audios(request.getAudios())
                .artists(request.getArtists())
                .genres(request.getGenres())
                .composers(request.getComposers())
                .build();
    }

    public SongRestResponse toDTO(Song song) {
        return SongRestResponse.builder()
                .id(song.getId())
                .alias(song.getAlias())
                .title(song.getTitle())
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
                .artists(song.getArtists())
                .genres(song.getGenres())
                .composers(song.getComposers())
                .audios(song.getAudios())
                .build();
    }
}
