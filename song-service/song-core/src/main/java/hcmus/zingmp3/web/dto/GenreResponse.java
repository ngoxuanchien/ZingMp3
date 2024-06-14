package hcmus.zingmp3.web.dto;

import java.util.UUID;

public record GenreResponse(
        UUID id,
        String alias,
        String name
) {
}
