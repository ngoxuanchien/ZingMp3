package hcmus.zingmp3.core.web.dto;

import hcmus.zingmp3.common.domain.model.SongStatus;
import hcmus.zingmp3.core.web.dto.validator.ArtistIdsExists;
import hcmus.zingmp3.core.web.dto.validator.GenreExists;
import hcmus.zingmp3.core.web.dto.validator.ImageExists;
import hcmus.zingmp3.core.web.dto.validator.MediaIdsExists;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.Length;

import java.util.Set;
import java.util.UUID;

public record SongRequest(
        @Null(
                message = "Id must be null",
                groups = {OnCreate.class}
        )
        UUID id,

        @NotNull(
                message = "Alias must not be null",
                groups = {OnCreate.class}
        )
        @Null(
                message = "Alias must be null",
                groups = {OnUpdate.class}
        )
        @Length(
                min = 1,
                max = 255,
                message = "Alias must be between {min} and {max} characters",
                groups = {OnCreate.class, OnUpdate.class}
        )
        String alias,

        @NotNull(
                message = "Title must not be null",
                groups = {OnCreate.class, OnUpdate.class}
        )
        @Length(
                min = 1,
                max = 255,
                message = "Title must be between {min} and {max} characters",
                groups = {OnCreate.class, OnUpdate.class}
        )
        String title,

        @NotNull(
                message = "ThumbnailId must not be null",
                groups = {OnUpdate.class}
        )
        @ImageExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        UUID thumbnailId,

        @ArtistIdsExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        Set<UUID> artistIds,

        @GenreExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        Set<UUID> genreIds,

        @ArtistIdsExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        Set<UUID> composerIds,

        @Null(
                message = "Status must be null",
                groups = {OnCreate.class}
        )
        SongStatus status,

        @NotNull(
                message = "ReleaseDate must not be null",
                groups = {OnUpdate.class, OnCreate.class}
        )
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
        Set<UUID> mediaIds
) {
}
