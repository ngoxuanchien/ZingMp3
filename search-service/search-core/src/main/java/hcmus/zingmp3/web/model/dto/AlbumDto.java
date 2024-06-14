package hcmus.zingmp3.web.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AlbumDto(
        UUID id,
        String alias,
        String title,
        UUID thumbnailId,
        String type,
        String description,
        LocalDateTime releaseDate
) implements Serializable {
}