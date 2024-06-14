package hcmus.zingmp3.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hcmus.zingmp3.common.domain.model.AlbumStatus;
import hcmus.zingmp3.common.domain.model.AlbumType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AlbumResponse(
        UUID id,
        String alias,
        String title,
        String thumbnail,
        AlbumType type,
        String description,
        List<ArtistResponse> artists,
        LocalDateTime releaseDate,
        AlbumStatus status,
        List<SongResponse> songs,
        LocalDateTime createDate,
        UUID createdBy,
        LocalDateTime lastModifiedDate,
        UUID lastModifiedBy
) {
}
