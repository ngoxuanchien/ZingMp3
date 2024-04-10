package hcmus.zingmp3.searchservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongDTO {
    private Integer id = 0;
    private String songName;
    private String songWriter;
    private String lyric;
    private String thumbnail;
    private byte[] songImage;
    private byte[] songFile;
    private int duration;
    private int played;
    private int liked;
    private String providedBy;
}
