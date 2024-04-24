package zingmp3.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class AlbumDto {
    private UUID id;
    private String title;
    private String thumbnail;
    private boolean isOfficial;
    private String link;
    private boolean isIndie;
    private LocalDateTime releaseDate;
    private String sortDescription;
    private LocalDateTime releasedAt;
    private List<UUID> genreIds;
    private boolean PR;
    private List<ArtistDto> artists;
    private String artistNames;
}
