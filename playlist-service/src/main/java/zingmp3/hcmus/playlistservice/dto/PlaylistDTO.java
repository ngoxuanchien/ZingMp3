package zingmp3.hcmus.playlistservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.function.ServerResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
    private String id;
    private String title;
    private String thumbnail;
    private boolean isOfficial;
    private String link;
    private LocalDate releaseDate;
    private String sortDescription;
    private int releasedAt;
    private String artistsNames;
    private Set<ArtistDTO> artists;
    private Set<GenreDTO> genres;
    private boolean isPrivate;
    private boolean isAlbum;
    private String textType;
    private String distributor;
    private String description;
    private String aliasTitle;
    private int like;
    private int listen;
    private Set<SongDTO> songs;
}
