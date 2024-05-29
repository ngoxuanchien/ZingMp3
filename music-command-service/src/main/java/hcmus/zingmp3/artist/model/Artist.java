package hcmus.zingmp3.artist.model;

import hcmus.zingmp3.award.model.Award;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "nvarchar(255) default 'No Name'", nullable = false)
    @Builder.Default
    private String name = "No Name";

    @Column(unique = true)
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

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "artist_award",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "award_id")
    )
    @Builder.Default
    private List<Award> awards = new LinkedList<>();

//    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "song_artist",
//            joinColumns = @JoinColumn(name = "artist_id"),
//            inverseJoinColumns = @JoinColumn(name = "song_id")
//    )
//    @Builder.Default
//    private List<Song> songs = new LinkedList<>();
//
//    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "song_composer",
//            joinColumns = @JoinColumn(name = "composer_id"),
//            inverseJoinColumns = @JoinColumn(name = "song_id")
//    )
//    @Builder.Default
//    private List<Song> composedSongs = new LinkedList<>();

    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID modifiedBy;
    private LocalDateTime modifiedAt;

}
