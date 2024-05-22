package hcmus.zingmp3.music_service.song;

import hcmus.zingmp3.music_service.song.model.Song;
import hcmus.zingmp3.music_service.song.model.SongRequest;
import hcmus.zingmp3.music_service.song.model.SongResponse;

import java.util.*;
import java.util.stream.IntStream;

import static hcmus.zingmp3.music_service.artist.ArtistDataForTest.AFTER_SAVE_ARTISTS;
import static hcmus.zingmp3.music_service.artist.ArtistDataForTest.ARTIST_RESPONSES;
import static hcmus.zingmp3.music_service.genre.GenreDataForTest.GENRE_AFTER_SAVE;
import static hcmus.zingmp3.music_service.genre.GenreDataForTest.GENRE_RESPONSES;

public class SongDataForTest {

    public static final List<UUID> SONG_IDS = IntStream.range(1, 10)
            .mapToObj(i -> UUID.randomUUID())
            .toList();

    public static final List<SongRequest> SONG_REQUESTS = IntStream.range(1, 10)
            .mapToObj(i -> SongRequest.builder()
                    .alias("alias-" + i)
                    .title("Title " + i)
                    .isOfficial(true)
                    .isWorldWide(true)
                    .isPrivate(false)
                    .releaseDate(i)
                    .distributor("Distributor " + i)
                    .hasLyric(true)
                    .build())
            .toList();

    public static final List<Song> SONG_BEFORE_SAVE = IntStream.range(1, 10)
            .mapToObj(i -> Song.builder()
                    .alias("alias-" + i)
                    .title("Title " + i)
                    .isOfficial(true)
//                    .artists(List.of(AFTER_SAVE_ARTISTS.get(i - 1)))
//                    .genres(List.of(GENRE_AFTER_SAVE.get(i - 1)))
                    .isWorldWide(true)
                    .duration(i)
                    .isPrivate(false)
                    .releaseDate(i)
                    .distributor("Distributor " + i)
                    .hasLyric(true)
                    .build())
            .toList();

    public static final List<Song> SONG_AFTER_SAVE = IntStream.range(1, 10)
            .mapToObj(i -> Song.builder()
                    .id(SONG_IDS.get(i - 1))
                    .alias("alias-" + i)
                    .title("Title " + i)
                    .isOfficial(true)
//                    .artists(List.of(AFTER_SAVE_ARTISTS.get(i - 1)))
//                    .genres(List.of(GENRE_AFTER_SAVE.get(i - 1)))
//                    .composers(List.of(AFTER_SAVE_ARTISTS.get(i - 1)))
                    .isWorldWide(true)
                    .duration(i)
                    .isPrivate(false)
                    .releaseDate(i)
                    .distributor("Distributor " + i)
                    .hasLyric(true)
                    .build())
            .toList();

    public static final List<SongResponse> SONG_RESPONSES = IntStream.range(1, 10)
            .mapToObj(i -> SongResponse.builder()
                    .id(SONG_IDS.get(i - 1))
                    .alias("alias-" + i)
                    .title("Title " + i)
                    .isOfficial(true)
//                    .artists(new HashSet<>(Collections.singletonList(ARTIST_RESPONSES.get(i - 1))))
//                    .genres(new HashSet<>(Collections.singletonList(GENRE_RESPONSES.get(i - 1))))
//                    .composers(new HashSet<>(Collections.singletonList(ARTIST_RESPONSES.get(i - 1))))
                    .isWorldWide(true)
                    .isPrivate(false)
                    .releaseDate(i)
                    .distributor("Distributor " + i)
                    .hasLyric(true)
                    .likes(SONG_AFTER_SAVE.get(i - 1).getLikes())
                    .listen(SONG_AFTER_SAVE.get(i - 1).getListen())
                    .comment(SONG_AFTER_SAVE.get(i - 1).getComment())
                    .build())
            .toList();
}
