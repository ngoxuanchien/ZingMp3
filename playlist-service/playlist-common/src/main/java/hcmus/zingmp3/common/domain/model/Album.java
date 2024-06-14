package hcmus.zingmp3.common.domain.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class Album extends AbstractPlaylist {

    {
        this.setAlbum(true);
    }

    private LocalDateTime releaseDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Setter(AccessLevel.NONE)
    private AlbumStatus status = AlbumStatus.APPROVAL_PENDING;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AlbumType albumType = AlbumType.ALBUM;

    public void approved() {
        status = AlbumStatus.APPROVED;
    }

    public void rejected() {
        status = AlbumStatus.REJECTED;
    }

    public void released() {
        status = AlbumStatus.RELEASED;
    }
}
