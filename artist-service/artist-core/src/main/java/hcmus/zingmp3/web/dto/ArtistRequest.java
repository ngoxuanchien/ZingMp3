package hcmus.zingmp3.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hcmus.zingmp3.web.dto.validator.artist.ArtistAliasNotExists;
import hcmus.zingmp3.web.dto.validator.image.ImageExists;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
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

        @ArtistAliasNotExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        @NotNull(
                message = "Alias is required",
                groups = {
                        OnCreate.class,
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

        @ImageExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        UUID thumbnailId,

        @NotNull(
                message = "Name is required",
                groups = {
                        OnCreate.class
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
                        OnCreate.class
                }
        )
        @Length(
                max = 255,
                message = "Real name length must smaller than {max}.",
                groups = {
                        OnCreate.class,
                        OnUpdate.class
                }
        )
        String realName
) implements Serializable {
}
