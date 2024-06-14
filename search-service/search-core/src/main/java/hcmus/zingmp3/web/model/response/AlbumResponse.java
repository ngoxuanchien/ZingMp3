package hcmus.zingmp3.web.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hcmus.zingmp3.web.model.dto.ArtistDto;
import hcmus.zingmp3.web.model.dto.SongDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link hcmus.zingmp3.domain.model.Album}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record AlbumResponse(
        UUID id,
        String alias,
        String title,
        UUID thumbnailId,
        String type,
        String description,
        Set<ArtistDto> artists,
        LocalDateTime releaseDate,
        Set<SongDto> songs
) implements Serializable {
}