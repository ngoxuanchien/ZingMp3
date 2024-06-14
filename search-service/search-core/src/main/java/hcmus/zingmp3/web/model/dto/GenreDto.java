package hcmus.zingmp3.web.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link hcmus.zingmp3.domain.model.Genre}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record GenreDto(UUID id, String alias, String name) implements Serializable {
}