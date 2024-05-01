package hcmus.zingmp3.playbackservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

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
