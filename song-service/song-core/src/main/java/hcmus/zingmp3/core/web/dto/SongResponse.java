package hcmus.zingmp3.core.web.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import hcmus.zingmp3.common.domain.model.SongStatus;

import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public record SongResponse(
        UUID id,
        String alias,
        String title,
        String thumbnail,
        Set<ArtistResponse> artists,
        Set<GenreResponse> genre,
        Set<ArtistResponse> composer,
        SongStatus status,
        Integer releaseDate,
        Integer listen,
        Integer liked,
        String lyric,
        Set<UUID> mediaIds
) {
}
