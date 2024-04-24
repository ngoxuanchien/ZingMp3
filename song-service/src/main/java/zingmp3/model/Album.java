package zingmp3.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String thumbnail;
    private boolean isOfficial;
    private String link;
    private boolean isIndie;
    private LocalDateTime releaseDate;
    private String sortDescription;
    private LocalDateTime releasedAt;
    @OneToMany
    @JoinColumn(name = "genre_id")
    private List<Genre> genres;
    private boolean PR;
    @OneToMany
    @JoinColumn(name = "artist_id")
    private List<Artist> artists;
    private String artistsNames;

}
