package zingmp3.hcmus.playlistservice.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int statusCode;
}
