package hcmus.zingmp3.common.domain.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.*;

@Table(name = "playlist")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class AbstractPlaylist {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(unique = true)
    private String alias;

    private String title;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID thumbnailId;

    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String description;

    @CollectionTable(name = "playlist_artist", joinColumns = @JoinColumn(name = "playlist_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "artist_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private List<UUID> artistIds = new ArrayList<>();

    @CollectionTable(name = "playlist_song", joinColumns = @JoinColumn(name = "playlist_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "song_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private List<UUID> songIds = new ArrayList<>();

    private boolean isPublic;

    private boolean isAlbum;

    @CreatedBy
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, updatable = false)
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

    public void setThumbnailId(UUID thumbnailId) {
        this.thumbnailId = Objects.requireNonNullElse(thumbnailId, UUID.fromString("00000000-0000-0000-0000-000000000000"));
    }

    public void setId(UUID id) {
        this.id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
    }

    public void setArtistIds(List<UUID> artistIds) {
        this.artistIds = Objects.requireNonNullElseGet(artistIds, ArrayList::new);
    }

    public void setSongIds(List<UUID> songIds) {
        this.songIds = Objects.requireNonNullElseGet(songIds, ArrayList::new);
    }
}
