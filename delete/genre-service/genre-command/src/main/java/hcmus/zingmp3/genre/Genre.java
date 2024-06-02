package hcmus.zingmp3.genre;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE genre SET is_deleted = true WHERE id=?")
@FilterDef(name = "deletedArtistFilter", parameters = @ParamDef(name = "is_deleted", type = Boolean.class))
@Filter(name = "deletedArtistFilter", condition = "is_deleted = :is_deleted")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "nvarchar(255) default 'No name'")
    @Builder.Default
    private String name = "No name";

    @Column(columnDefinition = "nvarchar(255) default 'No title'")
    @Builder.Default
    private String title = "No title";

    @Column(columnDefinition = "nvarchar(255)")
    private String alias;

    @ElementCollection
    @CollectionTable(name = "genre_songs", joinColumns = @JoinColumn(name = "genre_id"))
    @Column(name = "song_id")
    private Set<UUID> songs;


    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    @Builder.Default
    private boolean isDeleted = Boolean.FALSE;

    private UUID createdBy;
//    @CreatedDate
//    @Column(
//            updatable = false,
//            nullable = false
//    )
    private LocalDateTime createdAt;


    private UUID modifiedBy;

    private LocalDateTime modifiedAt;
}
