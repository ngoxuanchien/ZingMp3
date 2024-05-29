package hcmus.zingmp3.song.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hcmus.zingmp3.artist.model.ArtistResponse;
import hcmus.zingmp3.genre.model.GenreResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SongResponse {
    private UUID id;
    private String title;
    private String alias;
    private boolean isOfficial;
    @JsonIgnoreProperties("songs")
    @Builder.Default
    private Set<ArtistResponse> artists = new HashSet<>();
    @Builder.Default
    private Set<GenreResponse> genres = new HashSet<>();
    @Builder.Default
    private Set<ArtistResponse> composers = new HashSet<>();
    private boolean isWorldWide;
    private String thumbnail;
    private boolean isPrivate;
    private int releaseDate;
    private String distributor;
    private boolean hasLyric;
    private int likes;
    private int listen;
    private int comment;
    @Builder.Default
    private Map<Long, String> streaming = new HashMap<>();
}