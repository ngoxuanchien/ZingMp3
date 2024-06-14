package zingmp3.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

public record PasswordRequest(
        @Length(
                min = 8,
                message = "Password should have at least {min} characters",
                groups = {OnCreate.class, OnUpdate.class}
        )
        @Schema(format = "password")
        String password
) {
}
