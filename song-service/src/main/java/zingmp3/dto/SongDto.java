package zingmp3.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongDto {
        private UUID id;
        private String title;
        private String alias;
        private boolean isOfficial;
        private String username;
        private String artistsNames;
        private List<ArtistDto> artists;
        private boolean isWorldWide;
        private PreviewInfoDto previewInfo;
        private String thumbnailM;
        private String link;
        private String thumbnail;
        private Integer duration;
        private boolean zingChoice;
        private boolean isPrivate;
        private boolean preRelease;
        private LocalDateTime releaseDate;
        private List<GenreDto> genres;
        private String distributor;
        private List<IndicatorDto> indicators;
        private boolean isIndie;
        private Integer streamingStatus;
        private List<Integer> downloadPrivileges;
        private boolean allowAudioAds;
        private boolean hasLyric;
        private Integer userId;
        private List<ComposerDto> composers;
        private AlbumDto album;
        private boolean isRBT;
        private Integer like;
        private Integer listen;
        private boolean liked;
        private Integer comment;
        private StreamingDto streaming;
}