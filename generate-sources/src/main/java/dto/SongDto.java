package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.UUID;


@JsonIgnoreProperties(ignoreUnknown = true)
public record SongDto(
        UUID id,
        String alias,
        String title,
        String thumbnail,
        Integer releaseDate,
        Integer listen,
        Integer liked,
        String lyric,
        double duration,
        UUID albumId
) implements Serializable {
}