package zingmp3.hcmus.playlistservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistImageDTO {
    private String id;

    private String playlistId;
    private String name;
    private String type;
    private String filePath;
}
