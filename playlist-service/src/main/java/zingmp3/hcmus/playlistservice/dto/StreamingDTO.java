package zingmp3.hcmus.playlistservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StreamingDTO {
    private String id;
    private String url128kps;
    private String url320kps;
}
