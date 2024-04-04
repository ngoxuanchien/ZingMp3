package zingmp3.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class AuthenticatedRequest {
    private String username;
    private String password;
}
