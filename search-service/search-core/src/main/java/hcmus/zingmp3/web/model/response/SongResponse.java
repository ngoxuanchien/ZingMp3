package hcmus.zingmp3.web.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hcmus.zingmp3.web.model.dto.AlbumDto;
import hcmus.zingmp3.web.model.dto.ArtistDto;
import hcmus.zingmp3.web.model.dto.GenreDto;
import hcmus.zingmp3.web.model.dto.MediaDto;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link hcmus.zingmp3.domain.model.Song}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SongResponse(
        UUID id,
        String alias,
        String title,
        UUID thumbnailId,
        Set<ArtistDto> artists,
        Set<GenreDto> genres,
        Set<ArtistDto> composers,
        Integer releaseDate,
        Integer listen,
        Integer liked,
        String lyric,
        Set<MediaDto> streaming,
        double duration,
        AlbumDto album
) implements Serializable {
}