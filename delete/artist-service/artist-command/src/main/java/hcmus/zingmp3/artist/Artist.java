package hcmus.zingmp3.artist;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE artist SET is_deleted = true WHERE id=?")
@FilterDef(name = "deletedArtistFilter", parameters = @ParamDef(name = "is_deleted", type = Boolean.class))
@Filter(name = "deletedArtistFilter", condition = "is_deleted = :is_deleted")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "nvarchar(255) default 'No Name'", nullable = false)
    @Builder.Default
    private String name = "No Name";

    @Column(columnDefinition = "nvarchar(255)")
    private String alias;

    private UUID playlistId;

    @Builder.Default
    private UUID thumbnail = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @Column(columnDefinition = "int default 0", nullable = false)
    private int totalFollow;

    @Column(columnDefinition = "nvarchar(255)")
    private String sortBiography;

    @Column(columnDefinition = "nvarchar(10000)")
    private String biography;

    @Column(columnDefinition = "nvarchar(100)")
    private String national;

    @Column(columnDefinition = "nvarchar(100)")
    private String realName;

    private LocalDate birthday;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "artist_award", joinColumns = @JoinColumn(name = "artist_id"))
    @Column(name = "award_id")
    @Builder.Default
    private Set<UUID> awards = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)

    @CollectionTable(name = "artist_song", joinColumns = @JoinColumn(name = "artist_id"))
    @Builder.Default
    @Column(name = "song_id")
    private Set<UUID> songs = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "composer_song", joinColumns = @JoinColumn(name = "artist_id"))
    @Column(name = "song_id")
    @Builder.Default
    private Set<UUID> composedSongs = new HashSet<>();

    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID modifiedBy;
    private LocalDateTime modifiedAt;

    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE;
}
