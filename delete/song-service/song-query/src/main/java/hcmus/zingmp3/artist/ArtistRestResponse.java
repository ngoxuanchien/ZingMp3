package hcmus.zingmp3.artist;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ArtistRestResponse {
    private UUID id;
    private String name;
    private String alias;
    private UUID playlistId;
    private String thumbnail;
    private long totalFollow;
    private String sortBiography;
    private String biography;
    private String national;
    private String realName;
    private String birthday;
}
