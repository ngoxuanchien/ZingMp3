package hcmus.zingmp3.dto.album;

import com.fasterxml.jackson.annotation.JsonInclude;
import hcmus.zingmp3.dto.artist.ArtistResponse;
import hcmus.zingmp3.dto.song.SongResponse;

import java.time.LocalDateTime;
import java.util.List;
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
