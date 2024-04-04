package zingmp3.hcmus.playlistservice.dto.playlist;

import lombok.Data;

@Data
public class PlaylistCreationDTO {
    private String name;
    private boolean isRandomPlaying;
    private boolean isPublic;
}
