package hcmus.zingmp3.core.web.dto;

import hcmus.zingmp3.common.domain.model.SongStatus;
import jakarta.persistence.SecondaryTable;
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
                message = "Name must not be null",
                groups = {OnCreate.class, OnUpdate.class}
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
        UUID thumbnailId,

        Set<UUID> artistIds,

        Set<UUID> genreIds,

        Set<UUID> composerIds,

        @Null(
                message = "Status must be null",
                groups = {OnCreate.class}
        )
        SongStatus status,

        @Null(
                message = "ReleaseDate must be null",
                groups = {OnCreate.class}
        )
        @NotNull(
                message = "ReleaseDate must not be null",
                groups = {OnUpdate.class}
        )
        int releaseDate,

        @Null(
                message = "Listen must be null",
                groups = {OnCreate.class}
        )
        int listen,

        @Null(
                message = "Liked must be null",
                groups = {OnCreate.class}
        )
        int liked,

        @Length(
                min = 1,
                max = 65535,
                message = "Lyric must be between {min} and {max} characters",
                groups = {OnCreate.class, OnUpdate.class}
        )
        String lyric,

        Set<UUID> mediaIds
) {
}
