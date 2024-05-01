package hcmus.zingmp3.playbackservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StreamingFileDTO {
    private String id;
    private String alias;
    private String path128kps;
    private String path320kps;
}
