package zingmp3.hcmus.playlistservice.dto.album;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {
    private long id;
    private String name;
    private String url;
}
