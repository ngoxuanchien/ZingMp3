package hcmus.zingmp3.common.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    private UUID createdBy;

    private LocalDateTime createDate;

    void addSong(UUID songId) {
        if (songIds == null) {
            songIds = new HashSet<>();
        }

        songIds.add(songId);
    }

    void removeSong(UUID songId) {
        songIds.remove(songId);
    }

    void addArtist(UUID artistId) {
        if (artistIds == null) {
            artistIds = new HashSet<>();
        }

        artistIds.add(artistId);
    }

    void removeArtist(UUID artistId) {
        artistIds.remove(artistId);
    }
}
