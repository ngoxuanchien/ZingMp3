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
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Playlist {
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(unique = true)
    private String alias;

    private String title;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID thumbnailId;

    @Enumerated(EnumType.STRING)
    private PlaylistType type;

    private String description;

    @CollectionTable(name = "playlist_artist", joinColumns = @JoinColumn(name = "playlist_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "artist_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Builder.Default
    private Set<UUID> artistIds = new HashSet<>();

    @CollectionTable(name = "playlist_song", joinColumns = @JoinColumn(name = "playlist_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "song_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Builder.Default
    private Set<UUID> songIds = new HashSet<>();

    private boolean isPublic;

    @CreatedBy
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, updatable = false)
    private UUID createdBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;

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
        songIds.remove(songId);
    }

    public void addArtist(UUID artistId) {
        if (artistIds == null) {
            artistIds = new HashSet<>();
        }

        artistIds.add(artistId);
    }

    public void removeArtist(UUID artistId) {
        artistIds.remove(artistId);
    }

//    public void setThumbnailId(UUID thumbnailId) {
//        this.thumbnailId = Objects.requireNonNullElseGet(thumbnailId, UUID.fromString("00000000-0000-0000-0000-000000000000"));
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public void setArtistIds(Set<UUID> artistIds) {
//        this.artistIds = artistIds;
//    }
//
//    public void setSongIds(Set<UUID> songIds) {
//        this.songIds = songIds;
//    }
}
