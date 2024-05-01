package hcmus.zingmp3.model;


import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NonNull;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class Song {
    private static Integer currentId = 1;

    @Builder.Default
    private Integer id = currentId++;
    private String title;
    private String alias;
    private boolean isOfficial;
    private String artistsNames;
    private Set<Artist> artists;
    private boolean isWorldWide;
    private String thumbnail;
    private int duration;
    private boolean isPrivate;
    private int releaseDate;
    private String distributor;
    private Set<Genre> genres;
    private Set<Artist> composers;
    private boolean hasLyric;
    private int like;
    private int listen;
    private boolean liked;
    private int comment;
    private Streaming streaming;
}
