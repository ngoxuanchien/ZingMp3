package zingmp3.hcmus.playlistservice.dto.playlist;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(hidden = true)
    private UUID id;
    private String name;
    private boolean isPublic;
    private boolean isRandomPlaying;
    @Schema(hidden = true)
    private LocalDateTime modifiedDate;
    @Schema(hidden = true)
    private LocalDateTime createdDate;
    @Schema(hidden = true)
    private UUID createdBy;
}
