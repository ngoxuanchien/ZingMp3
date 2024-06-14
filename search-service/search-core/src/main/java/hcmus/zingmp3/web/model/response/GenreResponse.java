package hcmus.zingmp3.web.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link hcmus.zingmp3.domain.model.Genre}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record GenreResponse(UUID id, String alias, String name) implements Serializable {
}