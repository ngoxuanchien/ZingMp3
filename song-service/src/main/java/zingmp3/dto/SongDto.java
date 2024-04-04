package zingmp3.dto;


import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class SongDto {
    private Integer id;
    private String songName;
    private String songWriter;
    private String lyric;
    private String thumbnail;
    private int duration;
    private int played;
    private int liked;
    private String providedBy;
}
