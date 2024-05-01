package hcmus.zingmp3.music_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreamingDTO {
    private String id;
    private String url128kps;
    private String url320kps;
}
