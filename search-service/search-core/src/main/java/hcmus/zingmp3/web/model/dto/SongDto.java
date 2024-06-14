package hcmus.zingmp3.web.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link hcmus.zingmp3.domain.model.Song}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SongDto(
        UUID id,
        String alias,
        String title,
        UUID thumbnailId,
        Integer releaseDate,
        Integer listen,
        Integer liked,
        String lyric,
        double duration,
        UUID albumId
) implements Serializable {
}