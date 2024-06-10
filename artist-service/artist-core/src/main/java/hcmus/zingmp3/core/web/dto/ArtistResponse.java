package hcmus.zingmp3.core.web.dto;

import hcmus.zingmp3.common.domain.model.ArtistStatus;

import java.util.UUID;

public record ArtistResponse(
        UUID id,
        String alias,
        UUID thumbnailId,
        String name,
        String realName,
        ArtistStatus status,
        UUID createdBy
) {
}
