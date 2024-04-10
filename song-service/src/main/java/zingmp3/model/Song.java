package zingmp3.model;

import com.google.type.DateTime;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String songName;
    private String songWriter;
    @Column(name = "lyric", length = 10000)
    private String lyric;
    private String thumbnail;
    private byte[] songImage;
    private byte[] songFile;
    private int duration;
    @Builder.Default
    private int played = 0;

    @Builder.Default
    private int liked = 0;

    @Builder.Default
    private String providedBy = "";
    private DateTime releaseDate;

    private DateTime createdDate;
    private Integer createdBy;
    private DateTime modifiedDate;
    private Integer modifiedBy;
}
