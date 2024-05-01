package hcmus.zingmp3.playbackservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongImageDTO {
    private String id;
    private String songId;
    private String name;
    private String filePath;
}
