package hcmus.zingmp3.dto.song;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SongRequest(
        String alias,
        String title,
        UUID thumbnailId,
        List<UUID> artistIds,
        List<UUID> genreIds,
        List<UUID> composerIds,
        Integer releaseDate,
        Integer listen,
        Integer liked,
        String lyric,
        List<UUID> mediaIds
) {
}
