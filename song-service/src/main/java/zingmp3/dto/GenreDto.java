package zingmp3.dto;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class GenreDto {
    private UUID id;
    private String name;
    private String title;
    private String alias;
    private String link;
}
