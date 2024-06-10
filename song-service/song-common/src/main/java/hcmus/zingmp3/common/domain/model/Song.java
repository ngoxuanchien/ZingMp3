package hcmus.zingmp3.common.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
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
    @Enumerated(EnumType.STRING)
    private SongStatus status = SongStatus.APPROVAL_PENDING;

    private Integer releaseDate;

    @Builder.Default
    private Integer listen = 0;

    @Builder.Default
    private Integer liked = 0;

    @Column(columnDefinition = "TEXT")
    private String lyric;

    @CollectionTable(name = "song_artist", joinColumns = @JoinColumn(name = "song_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "media_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Set<UUID> mediaIds;

    private double duration;

    @CreatedBy
    @Column(
            updatable = false,
            nullable = false
    )
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID createdBy;

    @CreatedDate
    @Column(
            updatable = false,
            nullable = false
    )
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(
            insertable = false
    )
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID lastModifiedBy;

    @LastModifiedDate
    @Column(
            insertable = false
    )
    private LocalDateTime lastModifiedDate;

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

    public void setArtistIds(Set<UUID> artistIds) {
        this.artistIds = Objects.requireNonNullElseGet(artistIds, HashSet::new);
    }

    public void setGenreIds(Set<UUID> genreIds) {
        this.genreIds = Objects.requireNonNullElseGet(genreIds, HashSet::new);
    }

    public void setComposerIds(Set<UUID> composerIds) {
        this.composerIds = Objects.requireNonNullElseGet(composerIds, HashSet::new);
    }

    public void setMediaIds(Set<UUID> mediaIds) {
        this.mediaIds = Objects.requireNonNullElseGet(mediaIds, HashSet::new);
    }

    public void setThumbnailId(UUID thumbnailId) {
        this.thumbnailId = Objects.requireNonNullElse(thumbnailId, UUID.fromString("00000000-0000-0000-0000-000000000000"));
    }

    public void setListen(Integer listen) {
        this.listen = Objects.requireNonNullElse(listen, 0);
    }

    public void setLiked(Integer liked) {
        this.liked = Objects.requireNonNullElse(liked, 0);
    }

    public void setId(UUID id) {
        this.id = Objects.requireNonNullElse(id, UUID.randomUUID());
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = Objects.requireNonNullElse(releaseDate, 0);
    }
}
