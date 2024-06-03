package hcmus.zingmp3.core.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.UUID;

public record GenreRequest(
        @Null(
                message = "Id must be null",
                groups = OnCreate.class
        )
        UUID id,

        @NotNull(
                message = "Alias must not be null",
                groups = {
                        OnCreate.class,
                        OnUpdate.class
                }
        )
        String alias,

        @NotNull(
                message = "Name must not be null",
                groups = {
                        OnCreate.class,
                        OnUpdate.class
                }
        )
        String name
) {
}
