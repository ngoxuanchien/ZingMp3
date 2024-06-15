package hcmus.zingmp3.dto.artist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ArtistRequest(
        String alias,
        UUID thumbnailId,
        String name,
        String realName
) implements Serializable {
}
