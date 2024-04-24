package zingmp3.hcmus.playlistservice.dto.playlist;

import lombok.Builder;
import lombok.Data;
import zingmp3.hcmus.playlistservice.dto.song.SongDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class PlaylistDTO {
    private UUID id;
    private String name;
    private boolean isPublic;
    private boolean isRandomPlaying;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
    private UUID createdBy;
}
