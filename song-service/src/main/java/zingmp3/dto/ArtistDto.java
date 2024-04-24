package zingmp3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
public class ArtistDto {
        private UUID id;
        private String name;
        private String link;
        private boolean spotlight;
        private String alias;
        private String thumbnail;
        private String thumbnailM;
        private boolean isOA;
        private boolean isOABrand;
        private Integer totalFollow;
}
