package hcmus.zingmp3.common.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Song {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String alias;

    private String title;

    private UUID thumbnailId;

    @CollectionTable(name = "song_artist", joinColumns = @JoinColumn(name = "song_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "artist_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Set<UUID> artistIds;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "song_genre",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "song_composer", joinColumns = @JoinColumn(name = "song_id"))
    @Column(name = "composer_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Set<UUID> composerIds;

    private SongStatus status;

    private int releaseDate;

    @Builder.Default
    private int listen = 0;

    @Builder.Default
    private int liked = 0;

    @Column(columnDefinition = "TEXT")
    private String lyric;

    @CollectionTable(name = "song_artist", joinColumns = @JoinColumn(name = "song_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "media_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Set<UUID> mediaIds;

    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID modifiedBy;
    private LocalDateTime modifiedAt;
}
