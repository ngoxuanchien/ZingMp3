package hcmus.zingmp3.music_service.song.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hcmus.zingmp3.music_service.artist.model.ArtistResponse;
import hcmus.zingmp3.music_service.genre.model.GenreResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
    private Set<ArtistResponse> artists;
    private Set<GenreResponse> genres;
    private Set<ArtistResponse> composers;
    private boolean isWorldWide;
    private String thumbnail;
    private boolean isPrivate;
    private int releaseDate;
    private String distributor;
    private boolean hasLyric;
    private int likes;
    private int listen;
    private int comment;
    private Map<Long, String> streaming;
}