package zingmp3.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "song")
//@EntityListeners(AuditingEntityListener.class)
public class Song {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String alias;
    private boolean isOfficial;
    private String artistsNames;

    @JoinColumn(name = "artists")
    @OneToMany
    private Set<Artist> artists;

    private boolean isWorldWide;

    @OneToOne
    @JoinColumn(name = "preview_info")
    private PreviewInfo previewInfo;
    private String thumbnailM;
    private String link;
    private String thumbnail;
    private Integer duration;
    private boolean zingChoice;
    private boolean isPrivate;
    private boolean preRelease;
    private LocalDateTime releaseDate;

    @OneToMany
    @JoinColumn(name = "genre_id")
    private List<Genre> genres;

    private String distributor;

    @OneToMany
    @JoinColumn(name = "indicator")
    private List<Indicator> indicators;
    private boolean isIndie;
    private Integer streamingStatus;

    @ElementCollection
    @CollectionTable(name = "download_privileges", joinColumns = @JoinColumn(name = "song_id"))
    @Column(name = "download_privileges")
    private List<Integer> downloadPrivileges;

    private boolean allowAudioAds;
    private boolean hasLyric;
    private Integer userId;

    @OneToMany
    @JoinColumn(name = "composer_ids")
    private List<Composer> composers;

    @ManyToOne
    private Album album;

    private boolean isRBT;
    @Column(name = "like_count")
    private Integer like;
    private Integer listen;
    private boolean liked;
    private Integer comment;

    @OneToOne
    private Streaming streaming;

    @CreatedDate
    @Column(
//            nullable = false,
//            updatable = false
    )
    private LocalDateTime createdDate;

    @CreatedBy
    @Column(
//            nullable = false,
//            updatable = false
    )
    private UUID createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedDate;

    @LastModifiedBy
    @Column(insertable = false)
    private UUID modifiedBy;
}
