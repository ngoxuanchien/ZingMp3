package hcmus.zingmp3.dto.genre;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GenreRequest(
        String alias,
        String name
) {
}
