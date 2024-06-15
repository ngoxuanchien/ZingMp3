package hcmus.zingmp3.web.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import hcmus.zingmp3.common.domain.model.SongStatus;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
