package hcmus.zingmp3.dto.album;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record AlbumRequest(
        String alias,
        UUID thumbnailId,
        String title,
        AlbumType type,
        String description,
        List<UUID> artistIds,
        LocalDateTime releaseDate,
        List<UUID> songIds
) {
}
