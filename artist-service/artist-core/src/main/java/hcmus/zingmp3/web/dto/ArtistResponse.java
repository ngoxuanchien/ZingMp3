package hcmus.zingmp3.web.dto;

import hcmus.zingmp3.common.domain.model.ArtistStatus;

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
