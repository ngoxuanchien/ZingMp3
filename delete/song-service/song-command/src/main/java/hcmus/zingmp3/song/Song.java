package hcmus.zingmp3.song;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE artist SET is_deleted = true WHERE id=?")
@FilterDef(name = "deletedArtistFilter", parameters = @ParamDef(name = "is_deleted", type = Boolean.class))
@Filter(name = "deletedArtistFilter", condition = "is_deleted = :is_deleted")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    @Column(unique = true)
    private String alias;

    private boolean isOfficial;

    private boolean isWorldWide;

    @Builder.Default
    private UUID thumbnail = UUID.fromString("00000000-0000-0000-0000-000000000000");

    private int duration;

    private boolean isPrivate;

    @Builder.Default
    private int releaseDate = 0;

    private String distributor;

    private boolean hasLyric;

    @Builder.Default
    private int likes = 0;

    @Builder.Default
    private int listen = 0;

    @Builder.Default
    private int comment = 0;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "song_artist",
            joinColumns = @JoinColumn(name = "song_id")
    )
    @Column(name = "artist_id")
    private Set<UUID> artists;

    @ElementCollection(fetch = FetchType.EAGER)

    @CollectionTable(
            name = "song_genre",
            joinColumns = @JoinColumn(name = "song_id")
    )
    @Column(name = "genre_id")
    private Set<UUID> genres;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "song_composer",
            joinColumns = @JoinColumn(name = "song_id")
    )
    @Column(name = "artist_id")
    private Set<UUID> composers;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "song_audio",
            joinColumns = @JoinColumn(name = "song_id")
    )
    @Column(name = "audio_id")
    private Set<UUID> audios;

    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID modifiedBy;
    private LocalDateTime modifiedAt;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private boolean isDeleted = Boolean.FALSE;
}
