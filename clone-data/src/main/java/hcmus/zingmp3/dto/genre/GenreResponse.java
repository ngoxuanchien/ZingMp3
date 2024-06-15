package hcmus.zingmp3.dto.genre;

import java.util.UUID;

public record GenreResponse(
        UUID id,
        String alias,
        String name
) {
}
