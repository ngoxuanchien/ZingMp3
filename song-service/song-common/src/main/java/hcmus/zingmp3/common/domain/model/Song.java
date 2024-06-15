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
import java.util.*;

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
    private List<UUID> artistIds = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "song_genre",
            joinColumns = @JoinColumn(name = "song_id")
    )
    @Column(name = "genre_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Builder.Default
    private List<UUID> genreIds = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "song_composer", joinColumns = @JoinColumn(name = "song_id"))
    @Column(name = "composer_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Builder.Default
    private List<UUID> composerIds = new ArrayList<>();

    @Builder.Default
    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    private SongStatus status = SongStatus.APPROVAL_PENDING;

    private int releaseDate;

    @Builder.Default
    private Integer listen = 0;

    @Builder.Default
    private Integer liked = 0;

    @Column(columnDefinition = "TEXT")
    private String lyric;

    @CollectionTable(name = "song_medias", joinColumns = @JoinColumn(name = "song_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "media_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private List<UUID> mediaIds;

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
            artistIds = new ArrayList<>();
        }

        artistIds.add(artistId);
    }

    public void removeGenre(UUID genreId) {
        genreIds.remove(genreId);
    }

    public void addGenre(UUID genreId) {
        if (genreIds == null) {
            genreIds = new ArrayList<>();
        }

        genreIds.add(genreId);
    }

    public void removeComposer(UUID composerId) {
        composerIds.remove(composerId);
    }

    public void addComposer(UUID composerId) {
        if (composerIds == null) {
            composerIds = new ArrayList<>();
        }

        composerIds.add(composerId);
    }

    public void removeMedia(UUID mediaId) {
        mediaIds.remove(mediaId);
    }

    public void addMedia(UUID mediaId) {
        if (mediaIds == null) {
            mediaIds = new ArrayList<>();
        }

        mediaIds.add(mediaId);
    }

    public void setArtistIds(List<UUID> artistIds) {
        this.artistIds = Objects.requireNonNullElseGet(artistIds, ArrayList::new);
    }

    public void setGenreIds(List<UUID> genreIds) {
        this.genreIds = Objects.requireNonNullElseGet(genreIds, ArrayList::new);
    }

    public void setComposerIds(List<UUID> composerIds) {
        this.composerIds = Objects.requireNonNullElseGet(composerIds, ArrayList::new);
    }

    public void setMediaIds(List<UUID> mediaIds) {
        this.mediaIds = Objects.requireNonNullElseGet(mediaIds, ArrayList::new);
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

    public void setLyric(String lyric) {
        this.lyric = Objects.requireNonNullElse(lyric, "");
    }
}
