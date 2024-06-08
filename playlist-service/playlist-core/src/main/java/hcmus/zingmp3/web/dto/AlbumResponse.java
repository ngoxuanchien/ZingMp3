package hcmus.zingmp3.web.dto;

import hcmus.zingmp3.common.domain.model.AlbumStatus;
import hcmus.zingmp3.common.domain.model.AlbumType;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record AlbumResponse(
        UUID id,
        String alias,
        String title,
        AlbumType type,
        String description,
        Set<UUID> artistIds,
        LocalDateTime releaseDate,
        AlbumStatus status,
        Set<UUID> songIds,
        LocalDateTime createDate,
        UUID distributorId
) {
}
