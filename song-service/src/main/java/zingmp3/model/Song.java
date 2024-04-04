package zingmp3.model;

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
    private int duration;
    private int played = 0;
    private int liked = 0;
    private String providedBy;
}
