package zingmp3.hcmus.playlistservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreDTO {
    private String id;
    private String name;
    private String title;
    private String alias;
    private String link;
}
