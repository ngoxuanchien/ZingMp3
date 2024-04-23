package zingmp3.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongDTO {
    private Integer id;
    private String songName;
    private String songWriter;
    private String lyric;
    private String thumbnail;
    private String songImage;
    private String songFile;
    private int duration;
    private int played;
    private int liked;
    private String providedBy;
}
