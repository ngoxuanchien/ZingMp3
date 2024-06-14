package hcmus.zingmp3.domain.model;

import lombok.*;

import java.util.UUID;

@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Playlist extends AbstractPlaylist {

    {
        this.setAlbum(false);
    }

    private boolean isPublic;
}
