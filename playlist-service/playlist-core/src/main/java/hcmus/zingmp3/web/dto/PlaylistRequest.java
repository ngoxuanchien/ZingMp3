package hcmus.zingmp3.web.dto;

import hcmus.zingmp3.common.domain.model.PlaylistType;
import hcmus.zingmp3.web.dto.validator.artist.ArtistIdsExists;
import hcmus.zingmp3.web.dto.validator.image.ImageExists;
import hcmus.zingmp3.web.dto.validator.playlist.PlaylistAliasNotExists;
import hcmus.zingmp3.web.dto.validator.song.SongIdsExists;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record PlaylistRequest(
        @Null(
                message = "Id must be null",
                groups = {OnCreate.class}
        )
        @NotNull(
                message = "Id must not be null",
                groups = {OnUpdate.class}
        )
        UUID id,

        @NotNull(
                message = "Alias must not be null",
                groups = {OnCreate.class}
        )
        @PlaylistAliasNotExists(
                groups = {OnCreate.class, OnUpdate.class}
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

        @NotNull(
                message = "Type must not be null",
                groups = {OnCreate.class}
        )
        PlaylistType type,

        @Length(
                max = 1000,
                message = "Description must be less than {max} characters",
                groups = {OnCreate.class, OnUpdate.class}
        )
        String description,

        @ArtistIdsExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        List<UUID> artistIds,

        @SongIdsExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        List<UUID> songIds,

        @NotNull(
                message = "Public must not be null",
                groups = {OnCreate.class}
        )
        Boolean isPublic
) {
}
