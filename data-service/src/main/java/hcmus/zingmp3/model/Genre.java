package hcmus.zingmp3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
public class Genre {
    private static Integer currentId = 1;

    @Builder.Default
    private Integer id = currentId++;
    private String name;
    private String title;
    private String alias;
    private String link;
}
