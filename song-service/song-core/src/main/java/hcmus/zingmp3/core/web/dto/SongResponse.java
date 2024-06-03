package hcmus.zingmp3.core.web.dto;

import hcmus.zingmp3.common.domain.model.SongStatus;

import java.util.Set;
import java.util.UUID;

public record SongResponse(
        UUID id,
        String alias,
        String title,
        String thumbnail,
        Set<UUID> artistIds,
        Set<GenreResponse> genres,
        Set<UUID> composerIds,
        SongStatus status,
        int releaseDate,
        int listen,
        int liked,
        String lyric,
        Set<UUID> mediaIds
) {
}
