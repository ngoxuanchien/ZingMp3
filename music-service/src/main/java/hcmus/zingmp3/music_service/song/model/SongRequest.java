package hcmus.zingmp3.music_service.song.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongRequest {
    @NotBlank
    @Size(max = 255)
    private String title;

    @NotBlank
    @Size(max = 255)
    private String alias;

    @NotNull
    private boolean isOfficial;

    @Hidden
    private Set<String> artists = new HashSet<>();

    @Hidden
    private Set<String> genres = new HashSet<>();

    @Hidden
    private Set<String> composers = new HashSet<>();

    @NotNull
    private boolean isWorldWide;

    @UUID
    @Hidden
    private String thumbnail;
    @NotNull
    private boolean isPrivate;
    private int releaseDate;
    @Size(max = 255)
    private String distributor;
    private boolean hasLyric = false;
    @JsonIgnore
    private Set<@UUID String> audios;

    @Schema(hidden = true)
    @Min(0)
    private int likes;

    @Min(0)
    @Schema(hidden = true)
    private int listen;

    @Min(0)
    @Hidden
    private int comment;

    @JsonIgnore
    private MultipartFile thumbnailFile;

    @JsonIgnore
    private Set<MultipartFile> audioFiles = new HashSet<>();

    public void addAllArtists(Set<String> artists) {
        if (artists == null) {
            return;
        }
        this.artists.addAll(artists);
    }

    public void addAllGenres(Set<String> genres) {
        if (genres == null) {
            return;
        }
        this.genres.addAll(genres);
    }

    public void addAllComposers(Set<String> composers) {
        if (composers == null) {
            return;
        }
        this.composers.addAll(composers);
    }

    public void addAudioFiles(MultipartFile[] audioFiles) {
        if (audioFiles == null) {
            return;
        }

        Collections.addAll(this.audioFiles, audioFiles);
    }
}
