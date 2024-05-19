package zingmp3.hcmus.playlistservice.playlist.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "nvarchar(255)")
    @Builder.Default
    private String title = "No title";

    @Column(unique = true)
    private String alias;

    @Builder.Default
    private UUID thumbnail = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private boolean isOfficial;
    private boolean isIndie;
    private String releaseDate;
    private int releasedAt;

    @Column(columnDefinition = "nvarchar(255)")
    private String sortDescription;

    @ElementCollection
    @CollectionTable(name = "playlist_genre", joinColumns = @JoinColumn(name = "playlist_id"))
    @Column(name = "genre_id")
    private Set<UUID> genres;

    @ElementCollection
    @CollectionTable(name = "playlist_artist", joinColumns = @JoinColumn(name = "playlist_id"))
    @Column(name = "artist_id")
    private Set<UUID> artists;

    @Column(columnDefinition = "nvarchar(255)")
    private String artistNames;
    private boolean isPrivate;

    @Builder.Default
    private boolean isAlbum = false;

    @Builder.Default
    private boolean isSingle = false;

    @Column(columnDefinition = "nvarchar(255)")
    private String distributor;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Builder.Default
    private int likes = 0;

    @Builder.Default
    private int listens = 0;

    @ElementCollection
    @CollectionTable(name = "playlist_song", joinColumns = @JoinColumn(name = "playlist_id"))
    @Column(name = "song_id")
    private Set<UUID> songs;

    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID modifiedBy;
    private LocalDateTime modifiedAt;
}
