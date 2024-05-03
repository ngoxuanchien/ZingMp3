package zingmp3.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class UserRegistrationRequest {
    private String email;
    @Schema(format = "password")
    private String password;
    private String name;
    private String clientId;
    private String clientSecret;
}

