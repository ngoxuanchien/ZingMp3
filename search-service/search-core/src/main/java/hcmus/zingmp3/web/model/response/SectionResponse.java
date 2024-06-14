package hcmus.zingmp3.web.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hcmus.zingmp3.domain.section.SectionType;

import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SectionResponse(
        UUID id,
        String title,
        SectionType sectionType,
        List<?> items
) {
}
