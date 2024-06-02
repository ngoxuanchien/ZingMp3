package hcmus.zingmp3.model;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;

import java.util.UUID;

@Data
@Builder
public class Streaming {
    private static Integer currentId = 1;
    @Builder.Default
    private Integer id = currentId++;
    private String url128kps;
    private String url320kps;
}
