package zingmp3.hcmus.playlistservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table(name = "album_song")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumSongEntity implements Persistable<String> {
    @Id
    private String id = UUID.randomUUID().toString();

    private String albumId;

    private String songId;

    @Transient
    private boolean isNew = false;

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public AlbumSongEntity setAsNew() {
        this.isNew = true;
        return this;
    }
}
