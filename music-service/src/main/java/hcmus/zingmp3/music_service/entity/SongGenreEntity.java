package hcmus.zingmp3.music_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("song_genre")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongGenreEntity implements Persistable<String> {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String genreId;
    private String songId;

    @Transient
    private boolean isNew = false;

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public SongGenreEntity setAsNew() {
        this.isNew = true;
        return this;
    }

}
