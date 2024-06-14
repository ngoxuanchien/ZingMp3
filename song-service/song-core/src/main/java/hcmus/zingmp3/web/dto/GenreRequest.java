package hcmus.zingmp3.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hcmus.zingmp3.web.dto.validator.genre.GenreAliasNotExists;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GenreRequest(
        @Null(
                message = "Id must be null",
                groups = OnCreate.class
        )
        @NotNull(
                message = "Id must not be null",
                groups = OnUpdate.class
        )
        UUID id,

        @NotNull(
                message = "Alias must not be null",
                groups = {
                        OnCreate.class
                }
        )
        @Length(
                min = 1,
                max = 255,
                message = "Alias must be between {min} and {max} characters",
                groups = {
                        OnCreate.class,
                        OnUpdate.class
                }
        )
        @GenreAliasNotExists(
                groups = {
                        OnCreate.class,
                        OnUpdate.class
                }
        )
        String alias,

        @NotNull(
                message = "Name must not be null",
                groups = {
                        OnCreate.class
                }
        )
        @Length(
                min = 1,
                max = 255,
                message = "Name must be between {min} and {max} characters",
                groups = {
                        OnCreate.class,
                        OnUpdate.class
                }
        )
        String name
) {
}
