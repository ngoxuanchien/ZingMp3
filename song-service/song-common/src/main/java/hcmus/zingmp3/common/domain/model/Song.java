package hcmus.zingmp3.common.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.HashSet;
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
//    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String alias;

    private String title;

    private UUID thumbnailId;

    @CollectionTable(name = "song_artist", joinColumns = @JoinColumn(name = "song_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "artist_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Builder.Default
    private Set<UUID> artistIds = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "song_genre",
            joinColumns = @JoinColumn(name = "song_id")
    )
    @Column(name = "genre_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Builder.Default
    private Set<UUID> genreIds = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "song_composer", joinColumns = @JoinColumn(name = "song_id"))
    @Column(name = "composer_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Builder.Default
    private Set<UUID> composerIds = new HashSet<>();

    @Builder.Default
    @Setter(AccessLevel.NONE)
    private SongStatus status = SongStatus.APPROVAL_PENDING;

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

    public void approved() {
        this.status = SongStatus.APPROVED;
    }

    public void rejected() {
        this.status = SongStatus.REJECTED;
    }

    public void released() {
        this.status = SongStatus.RELEASED;
    }

    public void removeArtist(UUID artistId) {
        artistIds.remove(artistId);
    }

    public void addArtist(UUID artistId) {
        if (artistIds == null) {
            artistIds = new HashSet<>();
        }

        artistIds.add(artistId);
    }

    public void removeGenre(UUID genreId) {
        genreIds.remove(genreId);
    }

    public void addGenre(UUID genreId) {
        if (genreIds == null) {
            genreIds = new HashSet<>();
        }

        genreIds.add(genreId);
    }

    public void removeComposer(UUID composerId) {
        composerIds.remove(composerId);
    }

    public void addComposer(UUID composerId) {
        if (composerIds == null) {
            composerIds = new HashSet<>();
        }

        composerIds.add(composerId);
    }

    public void removeMedia(UUID mediaId) {
        mediaIds.remove(mediaId);
    }

    public void addMedia(UUID mediaId) {
        if (mediaIds == null) {
            mediaIds = new HashSet<>();
        }

        mediaIds.add(mediaId);
    }
}
