package hcmus.zingmp3.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Album extends AbstractPlaylist {
    {
        this.setAlbum(true);
    }

    private LocalDateTime releaseDate;

    private String status;

}
