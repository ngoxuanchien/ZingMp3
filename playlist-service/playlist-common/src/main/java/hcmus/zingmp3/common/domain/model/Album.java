package hcmus.zingmp3.common.domain.model;

import jakarta.persistence.*;
import lombok.*;
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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Album {
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(unique = true)
    private String alias;

    private String title;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID thumbnailId;

    @Enumerated(EnumType.STRING)
    private AlbumType type;

    private String description;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @CollectionTable(name = "album_artist", joinColumns = @JoinColumn(name = "album_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "artist_id")
    @Builder.Default
    private Set<UUID> artistIds = new HashSet<>();

    private LocalDateTime releaseDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Setter(AccessLevel.NONE)
    private AlbumStatus status = AlbumStatus.APPROVAL_PENDING;

    @CollectionTable(name = "album_genre", joinColumns = @JoinColumn(name = "album_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "song_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Builder.Default
    private Set<UUID> songIds = new HashSet<>();

    @CreatedBy
    @Column(nullable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID createdBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(insertable = false)
    private UUID lastModifiedBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    public void addSong(UUID songId) {
        if (songIds == null) {
            songIds = new HashSet<>();
        }

        songIds.add(songId);
    }

    public void removeSong(UUID songId) {
        if (songIds == null) {
            songIds = new HashSet<>();
            return;
        }

        songIds.remove(songId);
    }

    public void approved() {
        status = AlbumStatus.APPROVED;
    }

    public void rejected() {
        status = AlbumStatus.REJECTED;
    }

    public void released() {
        status = AlbumStatus.RELEASED;
    }

    public void setSongIds(Set<UUID> songIds) {
        this.songIds = Objects.requireNonNullElseGet(songIds, HashSet::new);
    }

    public void setArtistIds(Set<UUID> artistIds) {
        this.artistIds = Objects.requireNonNullElseGet(artistIds, HashSet::new);
    }
}
