package hcmus.zingmp3.music_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
    private String id;
    private String title;
    private String alias;
    private boolean isOfficial;
    private String artistsNames;
    private Set<ArtistDTO> artists;
    private boolean isWorldWide;
    private String thumbnail;
    private int duration;
    private boolean isPrivate;
    private int releaseDate;
    private String distributor;
    private Set<GenreDTO> genres;
    private Set<ArtistDTO> composers;
    private boolean hasLyric;
    private int likes;
    private int listen;
    private boolean liked;
    private int comment;
    private StreamingDTO streaming;
}
