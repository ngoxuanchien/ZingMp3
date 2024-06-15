package hcmus.zingmp3.web.dto;

import hcmus.zingmp3.common.domain.model.AlbumType;
import hcmus.zingmp3.web.dto.validator.album.AlbumAliasNotExists;
import hcmus.zingmp3.web.dto.validator.artist.ArtistIdsExists;
import hcmus.zingmp3.web.dto.validator.image.ImageExists;
import hcmus.zingmp3.web.dto.validator.song.SongIdsExists;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record AlbumRequest(
        @Null(
                message = "Id must be null",
                groups = {OnCreate.class}
        )
        @NotNull(
                message = "Id must not be null",
                groups = {OnUpdate.class}
        )
        UUID id,

        @AlbumAliasNotExists(
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

        @ImageExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        UUID thumbnailId,

        @NotNull(
                message = "Title must not be null",
                groups = {OnCreate.class}
        )
        @Length(
                min = 1,
                max = 255,
                message = "Title must be between {min} and {max} characters",
                groups = {OnCreate.class}
        )
        String title,

        @NotNull(
                message = "Album type must not be null",
                groups = {OnCreate.class}
        )
        AlbumType type,


        @Length(
                max = 1000,
                message = "Description must be less than {max} characters",
                groups = {OnCreate.class}
        )
        String description,

        @ArtistIdsExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        List<UUID> artistIds,

        LocalDateTime releaseDate,

        @SongIdsExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        List<UUID> songIds
) {
}
