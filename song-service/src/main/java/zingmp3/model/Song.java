package zingmp3.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "song")
@EntityListeners(AuditingEntityListener.class)
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String songName;
    private String songWriter;
    @Column(name = "lyric", length = 10000)
    private String lyric;
    private String thumbnail;
    private String songImage;
    private String songFile;
    private int duration;
    @Builder.Default
    private int played = 0;

    @Builder.Default
    private int liked = 0;

    @Builder.Default
    private String providedBy = "";
    private LocalDateTime releaseDate;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdDate;

    @CreatedBy
    @Column(
//            nullable = false,
            updatable = false
    )
    private UUID createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedDate;

    @LastModifiedBy
    @Column(insertable = false)
    private UUID modifiedBy;
}
