package zingmp3.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
