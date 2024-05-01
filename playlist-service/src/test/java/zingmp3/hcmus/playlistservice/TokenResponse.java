package zingmp3.hcmus.playlistservice;

import lombok.Data;

@Data
public class TokenResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private int refresh_expires_in;
    private String session_state;
    private String scope;

    // Getters and setters
}
