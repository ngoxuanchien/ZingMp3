package hcmus.zingmp3.web.dto;

import hcmus.zingmp3.common.domain.model.PlaylistType;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record PlaylistResponse(
        UUID id,
        String alias,
        String title,
        UUID thumbnailId,
        PlaylistType type,
        String description,
        Set<UUID> artistIds,
        Set<UUID> songIds,
        boolean isPublic,
        UUID createdBy,
        LocalDateTime createDate
) {
}
