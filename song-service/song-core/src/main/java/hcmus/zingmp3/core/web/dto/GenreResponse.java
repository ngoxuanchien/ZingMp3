package hcmus.zingmp3.core.web.dto;

import java.util.UUID;

public record GenreResponse(
        UUID id,
        String alias,
        String name
) {
}
