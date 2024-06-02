package hcmus.zingmp3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {
    private static Integer currentId = 1;

    @Builder.Default
    private Integer id = currentId++;

    private String title;
    private String thumbnail;
    private boolean isOfficial;
    private String link;
    private LocalDate releaseDate;
    private String sortDescription;
    private int releasedAt;
    private String artistsNames;
    private Set<Artist> artists;
    private Set<Genre> genres;
    private boolean isPrivate;
    private boolean isAlbum;
    private String textType;
    private String distributor;
    private String description;
    private String aliasTitle;
    private int like;
    private int listen;
    private Set<Song> songs;
}
