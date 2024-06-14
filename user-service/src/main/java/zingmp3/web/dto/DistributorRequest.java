package zingmp3.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.Length;
import zingmp3.web.dto.validator.image.ImageExists;

import java.time.LocalDateTime;
import java.util.UUID;

public record DistributorRequest(
        @Email(message = "Email should be valid", groups = {OnCreate.class, OnUpdate.class})
        @NotNull(
                message = "Email should not be null",
                groups = {OnCreate.class}
        )
        String email,

//        @NotNull(
//                message = "Password should not be null",
//                groups = {OnCreate.class}
//        )
//        @Length(
//                min = 8,
//                message = "Password should have at least {min} characters",
//                groups = {OnCreate.class, OnUpdate.class}
//        )
//        @Schema(format = "password")
//        String password,

        @Length(
                min = 1,
                max = 255,
                message = "Name should have at least {min} characters and at most {max} characters",
                groups = {OnCreate.class, OnUpdate.class}
        )
        String name,

        @Length(
                min = 1,
                max = 255,
                message = "Address should have at least {min} characters and at most {max} characters",
                groups = {OnCreate.class, OnUpdate.class}
        )
        String address,

        @Length(
                min = 1,
                max = 255,
                message = "Website should have at least {min} characters and at most {max} characters",
                groups = {OnCreate.class, OnUpdate.class}
        )
        String website,

        @ImageExists(
                groups = {OnCreate.class, OnUpdate.class}
        )
        UUID thumbnailId,

        @Schema(
                format = "date-time"
//                implementation = LocalDateTime.class
        )
        LocalDateTime timestamp,

        @Length(
                min = 1,
                max = 255,
                message = "Contract should have at least {min} characters and at most {max} characters",
                groups = {OnCreate.class, OnUpdate.class}
        )
        String contract

//        @NotNull(
//                message = "Client ID should not be null",
//                groups = {OnCreate.class}
//        )
//        @Length(
//                min = 1,
//                max = 255,
//                message = "Client ID should have at least {min} characters and at most {max} characters",
//                groups = {OnCreate.class}
//        )
//        @Null(
//                message = "Client secret must be null",
//                groups = {OnUpdate.class}
//        )
//        String clientId,
//
//        @NotNull(
//                message = "Client secret should not be null",
//                groups = {OnCreate.class}
//        )
//        @Length(
//                min = 1,
//                max = 255,
//                message = "Client secret should have at least {min} characters and at most {max} characters",
//                groups = {OnCreate.class}
//        )
//        @Null(
//                message = "Client secret must be null",
//                groups = {OnUpdate.class}
//        )
//        String clientSecret
) {
}
