package hcmus.zingmp3.artist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hcmus.zingmp3.song.model.SongResponse;
import hcmus.zingmp3.song.model.SongResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistResponse {
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

    private Set<String> awards;

    @JsonIgnoreProperties(value = { "artists", "streaming"})
    private Set<SongResponse> songs;
}
