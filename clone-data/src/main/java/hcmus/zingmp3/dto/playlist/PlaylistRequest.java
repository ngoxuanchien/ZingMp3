package hcmus.zingmp3.dto.playlist;

import java.util.List;
import java.util.UUID;

public record PlaylistRequest(
        String alias,
        String title,
        UUID thumbnailId,
        PlaylistType type,
        String description,
        List<UUID> artistIds,
        List<UUID> songIds,
        Boolean isPublic
) {
}
