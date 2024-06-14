package hcmus.zingmp3.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hcmus.zingmp3.common.domain.model.PlaylistType;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PlaylistResponse(
        UUID id,
        String alias,
        String title,
        String thumbnail,
        PlaylistType type,
        String description,
        Set<ArtistResponse> artists,
        Set<SongResponse> songs,
        boolean isPublic,
        UUID createdBy,
        LocalDateTime createDate,
        UUID lastModifiedBy,
        LocalDateTime lastModifiedDate
) {
}
