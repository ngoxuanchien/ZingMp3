package zingmp3.hcmus.playlistservice.dto.playlist;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlaylistInListDTO {
    private long id;
    private String name;
    private LocalDateTime updatedAt;
    private String ownerName;
    private boolean isOwner;
}
