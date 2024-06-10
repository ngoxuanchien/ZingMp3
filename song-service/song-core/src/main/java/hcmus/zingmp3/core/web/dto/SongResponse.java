package hcmus.zingmp3.core.web.dto;

import hcmus.zingmp3.common.domain.model.SongStatus;

import java.util.Set;
import java.util.UUID;

public record SongResponse(
        UUID id,
        String alias,
        String title,
        UUID thumbnailId,
        Set<UUID> artistIds,
        Set<UUID> genreIds,
        Set<UUID> composerIds,
        SongStatus status,
        Integer releaseDate,
        Integer listen,
        Integer liked,
        String lyric,
        Set<UUID> mediaIds
) {
}
