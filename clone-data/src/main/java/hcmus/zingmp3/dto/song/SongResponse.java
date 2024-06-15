package hcmus.zingmp3.dto.song;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import hcmus.zingmp3.dto.artist.ArtistResponse;
import hcmus.zingmp3.dto.genre.GenreResponse;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public record SongResponse(
        UUID id,
        String alias,
        String title,
        String thumbnail,
        List<ArtistResponse> artists,
        List<GenreResponse> genre,
        List<ArtistResponse> composer,
        SongStatus status,
        Integer releaseDate,
        Integer listen,
        Integer liked,
        String lyric,
        List<UUID> mediaIds,
        Double duration
) {
}
