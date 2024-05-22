package hcmus.zingmp3.music_service.artist;

import hcmus.zingmp3.music_service.artist.model.Artist;
import hcmus.zingmp3.music_service.artist.model.ArtistRequest;
import hcmus.zingmp3.music_service.artist.model.ArtistResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArtistDataForTest {
    static final List<UUID> ARTIST_IDS = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> UUID.randomUUID())
            .collect(Collectors.toList());

    static final List<ArtistRequest> ARTIST_REQUESTS = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> ArtistRequest.builder()
                    .name("Artist " + i)
                    .alias("alias-" + i)
                    .birthday(LocalDate.of(1980 + i, (i % 12) + 1, (i % 28) + 1))
                    .national("National " + i)
                    .realName("Real Name " + i)
                    .sortBiography("Sort Biography " + i)
                    .biography("Biography " + i)
                    .build())
            .collect(Collectors.toList());

    static final List<Artist> BEFORE_SAVE_ARTISTS = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> Artist.builder()
                    .name("Artist " + i)
                    .alias("alias-" + i)
                    .birthday(LocalDate.of(1980 + i, (i % 12) + 1, (i % 28) + 1))
                    .national("National " + i)
                    .realName("Real Name " + i)
                    .sortBiography("Sort Biography " + i)
                    .biography("Biography " + i)
                    .build())
            .collect(Collectors.toList());

    static final List<Artist> AFTER_SAVE_ARTISTS = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> Artist.builder()
                    .id(ARTIST_IDS.get(i - 1))
                    .name("Artist " + i)
                    .alias("alias-" + i)
                    .birthday(LocalDate.of(1980 + i, (i % 12) + 1, (i % 28) + 1))
                    .national("National " + i)
                    .realName("Real Name " + i)
                    .sortBiography("Sort Biography " + i)
                    .biography("Biography " + i)
                    .build())
            .collect(Collectors.toList());

    static final List<ArtistResponse> ARTIST_RESPONSES = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> ArtistResponse.builder()
                    .id(ARTIST_IDS.get(i - 1))
                    .name("Artist " + i)
                    .alias("alias-" + i)
                    .birthday(LocalDate.of(1980 + i, (i % 12) + 1, (i % 28) + 1).toString())
                    .national("National " + i)
                    .realName("Real Name " + i)
                    .sortBiography("Sort Biography " + i)
                    .biography("Biography " + i)
                    .build())
            .collect(Collectors.toList());
}
