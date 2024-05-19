package zingmp3.hcmus.playlistservice.playlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistRequest {
    @Size(max = 255)
    private String title;

    @Size(max = 255)
    @NotBlank
    private String alias;

    @UUID
    @Hidden
    private String thumbnail;
    private boolean isOfficial;
    private boolean isIndie;

    @Size(max = 255)
    private String releaseDate;
    private int releasedAt;

    @Size(max = 255)
    private String sortDescription;

    @Size(max = 255)
    private String artistNames;
    private boolean isPrivate;
    private boolean isAlbum;
    private boolean isSingle;

    @Size(max = 255)
    private String distributor;

    @Size(max = 1000)
    private String description;

    @Hidden
    private int likes;

    @Hidden
    private int listens;

    @Hidden
    private Set<String> songs = new HashSet<>();

    @Hidden
    private Set<String> artists = new HashSet<>();

    @Hidden
    private Set<String> genres = new HashSet<>();

    @JsonIgnore
    private MultipartFile thumbnailFile;

    public void setThumbnailFile(MultipartFile thumbnailFile) {
        if (thumbnailFile == null) {
            return;
        }

        this.thumbnailFile = thumbnailFile;
    }

    public PlaylistRequest addAllSong(Set<String> songs) {
        if (songs == null) {
            return this;
        }

        this.songs.addAll(songs);
        return this;
    }

    public PlaylistRequest addAllArtist(Set<String> artists) {
        if (artists == null) {
            return this;
        }

        this.artists.addAll(artists);
        return this;
    }

    public PlaylistRequest addAllGenre(Set<String> genres) {
        if (genres == null) {
            return this;
        }

        this.genres.addAll(genres);
        return this;
    }
}
