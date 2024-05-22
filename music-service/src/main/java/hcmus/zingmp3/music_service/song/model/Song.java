package hcmus.zingmp3.music_service.song.model;

import hcmus.zingmp3.music_service.artist.model.Artist;
import hcmus.zingmp3.music_service.audio.model.Audio;
import hcmus.zingmp3.music_service.genre.model.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    @Column(unique = true)
    private String alias;

    private boolean isOfficial;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "song_artist",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> artists;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "song_genre",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "song_composer",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "composer_id")
    )
    private List<Artist> composers;

    private boolean isWorldWide;

    private UUID thumbnail = UUID.fromString("00000000-0000-0000-0000-000000000000");

    private int duration;

    private boolean isPrivate;

    private int releaseDate = 0;

    private String distributor;

    private boolean hasLyric;

    @Builder.Default
    private int likes = 0;

    @Builder.Default
    private int listen = 0;

    @Builder.Default
    private int comment = 0;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "song_id")
    private List<Audio> audios;

    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID modifiedBy;
    private LocalDateTime modifiedAt;
}
