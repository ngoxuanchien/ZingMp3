package zingmp3.hcmus.playlistservice.playlist.model;

import hcmus.zingmp3.SongResponse;
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
public class PlaylistResponse {
    private UUID id;
    private String title;
    private String alias;
    private boolean isOfficial;
    private boolean isIndie;
    private String releaseDate;
    private int releasedAt;
    private String sortDescription;
    private String description;
    private String artistName;
    private boolean isPrivate;
    private boolean isAlbum;
    private boolean isSingle;
    private String distributor;
    private int likes;
    private int listens;
    private String thumbnail;
//    private Set<ArtistResponse> artists;
//    private Set<GenreResponse> genres;
    private Set<Map<String, String>> songs;
}
