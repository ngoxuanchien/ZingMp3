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
@Table(name = "playlist_genre")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistGenreEntity implements Persistable<String> {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String playlistId;
    private String genreId;

    @Transient
    private boolean isNew = false;

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public PlaylistGenreEntity setAsNew() {
        this.isNew = true;
        return this;
    }
}
