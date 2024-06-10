package zingmp3.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.Length;

public record UserRequest(
        @Email(message = "Email should be valid", groups = {OnCreate.class, OnUpdate.class})
        @NotNull(
                message = "Email should not be null",
                groups = {OnCreate.class}
        )
        String email,

        @NotNull(
                message = "Password should not be null",
                groups = {OnCreate.class}
        )
        @Length(
                min = 8,
                message = "Password should have at least {min} characters",
                groups = {OnCreate.class, OnUpdate.class}
        )
        @Schema(format = "password")
        String password,

        @Length(
                min = 1,
                max = 255,
                message = "Name should have at least {min} characters and at most {max} characters",
                groups = {OnCreate.class, OnUpdate.class}
        )
        String name,

        @NotNull(
                message = "Client ID should not be null",
                groups = {OnCreate.class}
        )
        @Length(
                min = 1,
                max = 255,
                message = "Client ID should have at least {min} characters and at most {max} characters",
                groups = {OnCreate.class}
        )
        @Null(
                message = "Client secret must be null",
                groups = {OnUpdate.class}
        )
        String clientId,

        @NotNull(
                message = "Client secret should not be null",
                groups = {OnCreate.class}
        )
        @Length(
                min = 1,
                max = 255,
                message = "Client secret should have at least {min} characters and at most {max} characters",
                groups = {OnCreate.class}
        )
        @Null(
                message = "Client secret must be null",
                groups = {OnUpdate.class}
        )
        String clientSecret
) {
}
