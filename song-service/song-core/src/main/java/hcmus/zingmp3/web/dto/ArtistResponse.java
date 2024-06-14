package hcmus.zingmp3.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ArtistResponse(
        UUID id,
        String alias,
        String name,
        String thumbnail,
        String realName
) implements Serializable {
}