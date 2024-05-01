package hcmus.zingmp3.playbackservice.dto;

import jakarta.annotation.sql.DataSourceDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

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
