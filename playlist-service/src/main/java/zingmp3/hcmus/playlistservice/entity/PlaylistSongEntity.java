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

@Table(name = "playlist_song")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistSongEntity implements Persistable<String> {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String songId;

    private String playlistId;

    @Transient
    private boolean isNew = false;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public PlaylistSongEntity setAsNew() {
        this.isNew = true;
        return this;
    }
}
