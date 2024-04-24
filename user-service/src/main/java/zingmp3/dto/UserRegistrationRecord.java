package zingmp3.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Data
public class UserRegistrationRecord {
    private String email;
    @Schema(format = "password")
    private String password;
    private String name;
}

