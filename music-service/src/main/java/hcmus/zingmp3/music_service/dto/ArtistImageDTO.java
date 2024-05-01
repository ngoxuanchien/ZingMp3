package hcmus.zingmp3.music_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtistImageDTO {
    private String id;

    private String artistId;
    private String name;
    private String type;
    private String filePath;
}
