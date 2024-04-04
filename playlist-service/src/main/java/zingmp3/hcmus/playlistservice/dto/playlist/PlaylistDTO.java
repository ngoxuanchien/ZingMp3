package zingmp3.hcmus.playlistservice.dto.playlist;

import lombok.Data;
import zingmp3.hcmus.playlistservice.dto.song.SongDTO;

import java.util.List;

@Data
public class PlaylistDTO {
    private long id;
    private String name;
    private int duration;
    private int songsCount;
    private boolean isPublic;
    private int updatedAt;
    private String ownerName;
    List<SongDTO> songs;
}
