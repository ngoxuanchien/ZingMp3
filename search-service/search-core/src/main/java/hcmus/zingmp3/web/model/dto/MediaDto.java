package hcmus.zingmp3.web.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hcmus.zingmp3.domain.model.Media;

import java.io.Serializable;

/**
 * DTO for {@link Media}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record MediaDto(Integer quality, String url) implements Serializable {
}