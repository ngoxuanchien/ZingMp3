package hcmus.zingmp3.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hcmus.zingmp3.web.dto.validator.artist.ArtistIdsExists;
import hcmus.zingmp3.web.dto.validator.genre.GenreExists;
import hcmus.zingmp3.web.dto.validator.image.ImageExists;
import hcmus.zingmp3.web.dto.validator.media.MediaIdsExists;
import hcmus.zingmp3.web.dto.validator.song.SongAliasNotExists;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SongRequest(
        @Null(
                message = "Id must be null",
                groups = {OnCreate.class}
        )
        @NotNull(
                message = "Id must not be null",
                groups = {OnUpdate.class}
        )
        UUID id,

        @SongAliasNotExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        @NotNull(
                message = "Alias must not be null",
                groups = {OnCreate.class}
        )
        @Length(
                min = 1,
                max = 255,
                message = "Alias must be between {min} and {max} characters",
                groups = {OnCreate.class}
        )
        String alias,

        @NotNull(
                message = "Title must not be null",
                groups = {OnCreate.class}
        )
        @Length(
                min = 1,
                max = 255,
                message = "Title must be between {min} and {max} characters",
                groups = {OnCreate.class, OnUpdate.class}
        )
        String title,

        @ImageExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        UUID thumbnailId,

        @ArtistIdsExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        List<UUID> artistIds,

        @GenreExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        List<UUID> genreIds,

        @ArtistIdsExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        List<UUID> composerIds,

//        @NotNull(
//                message = "ReleaseDate must not be null",
//                groups = {OnCreate.class}
//        )
        Integer releaseDate,

        @Null(
                message = "Listen must be null",
                groups = {OnCreate.class}
        )
        Integer listen,

        @Null(
                message = "Liked must be null",
                groups = {OnCreate.class}
        )
        Integer liked,

        @Length(
                min = 1,
                max = 65535,
                message = "Lyric must be between {min} and {max} characters",
                groups = {OnCreate.class, OnUpdate.class}
        )
        String lyric,

        @MediaIdsExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        List<UUID> mediaIds
) {
}
