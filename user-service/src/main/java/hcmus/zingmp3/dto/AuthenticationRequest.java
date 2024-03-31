package hcmus.zingmp3.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class AuthenticationRequest {
    private String username;
    private String password;
}
