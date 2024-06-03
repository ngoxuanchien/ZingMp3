package hcmus.zingmp3.core.web.dto;

import hcmus.zingmp3.common.domain.model.ArtistStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record ArtistRequest(
        @NotNull(
                message = "Id must be not null.",
                groups = OnUpdate.class
        )
        @Null(
                message = "Id must be null.",
                groups = OnCreate.class
        )
        UUID id,

        @NotNull(
                message = "Alias is required",
                groups = {
                        OnCreate.class,
                        OnUpdate.class
                }
        )
        @Length(
                min = 1,
                max = 255,
                message = "Name length must be between {min} and {max}.",
                groups = {
                        OnCreate.class,
                        OnUpdate.class
                }
        )
        String alias,

        @NotNull(
                message = "Thumbnail id is required",
                groups = {
                        OnUpdate.class
                }
        )
        UUID thumbnailId,

        @Null(
                message = "Status must be null.",
                groups = OnCreate.class
        )
        @NotNull(
                message = "Status must be not null.",
                groups = OnUpdate.class
        )
        ArtistStatus status,

        @NotNull(
                message = "Name is required",
                groups = {
                        OnCreate.class,
                        OnUpdate.class
                }
        )
        @Length(
                min = 1,
                max = 255,
                message = "Name length must be between {min} and {max}.",
                groups = {
                        OnCreate.class,
                        OnUpdate.class
                }
        )
        String name,

        @NotNull(
                message = "Real name is required",
                groups = {
                        OnCreate.class,
                        OnUpdate.class
                }
        )
        @Length(
                min = 1,
                max = 255,
                message = "Real name length must be between {min} and {max}.",
                groups = {
                        OnCreate.class,
                        OnUpdate.class
                }
        )
        String realName
) {
}
