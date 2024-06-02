package hcmus.zingmp3.song;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hcmus.zingmp3.artist.ArtistRestResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SongRestResponse {
    private UUID id;
    private String title;
    private String alias;
    private boolean isOfficial;
    @JsonIgnoreProperties("songs")
//    @Builder.Default
//    private Set<ArtistResponse> artists = new HashSet<>();
//    @Builder.Default
//    private Set<GenreResponse> genres = new HashSet<>();
//    @Builder.Default
//    private Set<ArtistResponse> composers = new HashSet<>();
    private boolean isWorldWide;
    private UUID thumbnail;
    private boolean isPrivate;
    private int releaseDate;
    private String distributor;
    private boolean hasLyric;
    private int likes;
    private int listen;
    private int comment;
    private Set<ArtistRestResponse> artists;
    private Set<UUID> genres;
    private Set<ArtistRestResponse> composers;
    private Set<UUID> audios;
    @Builder.Default
    private Map<Long, String> streaming = new HashMap<>();
}