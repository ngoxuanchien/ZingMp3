package hcmus.zingmp3.web.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hcmus.zingmp3.web.model.dto.SongDto;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link hcmus.zingmp3.domain.model.Artist}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ArtistResponse(
                UUID id,
                String alias,
                String name,
                UUID thumbnailId,
                String realName,
                Set<SongDto> songs,
                Set<SongDto> composedSongs
) implements Serializable {
}