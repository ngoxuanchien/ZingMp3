package hcmus.zingmp3.music_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreDTO {
    private String id;
    private String name;
    private String title;
    private String alias;
    private String link;
}
