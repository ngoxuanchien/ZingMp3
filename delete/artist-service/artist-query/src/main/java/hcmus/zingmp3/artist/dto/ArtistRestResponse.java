package hcmus.zingmp3.artist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hcmus.zingmp3.award.AwardRestResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistRestResponse {
    private UUID id;
    private String name;
    private String alias;
    private UUID playlistId;
    private String thumbnail;
    private int totalFollow;
    private String sortBiography;
    private String biography;
    private String national;
    private String realName;
    private String birthday;
//    private Set<UUID> awards;
    private Set<UUID> songs;
    private Set<UUID> composedSongs;

    @Builder.Default
    private Set<AwardRestResponse> awards = new HashSet<>();
}
