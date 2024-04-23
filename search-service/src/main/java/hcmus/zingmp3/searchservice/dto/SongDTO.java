package hcmus.zingmp3.searchservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Song")
public class SongDTO implements Serializable {
    @Id
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
