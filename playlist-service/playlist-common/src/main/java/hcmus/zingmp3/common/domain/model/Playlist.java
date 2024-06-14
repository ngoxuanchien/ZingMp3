package hcmus.zingmp3.common.domain.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class Playlist extends AbstractPlaylist {

    {
        this.setAlbum(false);
    }

    @Enumerated(EnumType.STRING)
    private PlaylistType playlistType;

    public void setType(PlaylistType type) {
        this.playlistType = Objects.requireNonNullElse(type, PlaylistType.USER_PLAYLIST);
    }
}
