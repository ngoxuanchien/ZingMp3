package hcmus.zingmp3.dto.artist;

import java.util.UUID;

public record ArtistResponse(
        UUID id,
        String alias,
        String thumbnail,
        String name,
        String realName,
        ArtistStatus status
) {
}
