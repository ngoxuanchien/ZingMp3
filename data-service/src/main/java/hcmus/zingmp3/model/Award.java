package hcmus.zingmp3.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Award {
    private static Integer currentId = 1;
    @Builder.Default
    private Integer id = currentId++;

    private String name;
}
