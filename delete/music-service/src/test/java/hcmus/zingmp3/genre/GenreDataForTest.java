package hcmus.zingmp3.genre;


import hcmus.zingmp3.genre.model.Genre;
import hcmus.zingmp3.genre.model.GenreRequest;
import hcmus.zingmp3.genre.model.GenreResponse;
import org.bouncycastle.pqc.crypto.newhope.NHSecretKeyProcessor;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

public class GenreDataForTest {
    public static final List<UUID> GENRE_IDS = IntStream.range(1, 10)
            .mapToObj(i -> UUID.randomUUID())
            .toList();

    public static final List<GenreRequest> GENRE_REQUESTS = IntStream.range(1, 10)
            .mapToObj(i -> GenreRequest.builder()
                    .alias("genre-" + i)
                    .name("Genre " + i)
                    .title("Title " + i)
                    .build())
            .toList();

    public static final List<Genre> GENRE_BEFORE_SAVE = IntStream.range(1, 10)
            .mapToObj(i -> Genre.builder()
                    .alias("genre-" + i)
                    .name("Genre " + i)
                    .title("Title " + i)
                    .build())
            .toList();

    public static final List<Genre> GENRE_AFTER_SAVE = IntStream.range(1, 10)
            .mapToObj(i -> Genre.builder()
                    .id(GENRE_IDS.get(i - 1))
                    .alias("genre-" + i)
                    .name("Genre " + i)
                    .title("Title " + i)
                    .build())
            .toList();

    public static final List<GenreResponse> GENRE_RESPONSES = IntStream.range(1, 10)
            .mapToObj(i -> GenreResponse.builder()
                    .id(GENRE_IDS.get(i - 1))
                    .alias("genre-" + i)
                    .name("Genre " + i)
                    .title("Title " + i)
                    .build())
            .toList();
}
