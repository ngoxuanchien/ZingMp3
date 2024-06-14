package hcmus.zingmp3.web.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link hcmus.zingmp3.domain.model.Artist}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ArtistDto(
        UUID id,
        String alias,
        String name,
        UUID thumbnailId,
        String realName
) implements Serializable {
}