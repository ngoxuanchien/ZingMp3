package hcmus.zingmp3.dto.playlist;

import com.fasterxml.jackson.annotation.JsonInclude;
import hcmus.zingmp3.dto.artist.ArtistResponse;
import hcmus.zingmp3.dto.song.SongResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PlaylistResponse(
        UUID id,
        String alias,
        String title,
        String thumbnail,
        PlaylistType type,
        String description,
        List<ArtistResponse> artists,
        List<SongResponse> songs,
        boolean isPublic,
        UUID createdBy,
        LocalDateTime createDate,
        UUID lastModifiedBy,
        LocalDateTime lastModifiedDate
) {
}
